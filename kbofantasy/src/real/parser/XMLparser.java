package real.parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import openapi.NaverSearchAPI;
import real.dto.NewsDTO;

public class XMLparser {
	
	public static String[] getdate(String pubdate){
		/* 년: 0
		 * 월: 1
		 * 일: 2
		 * Tue, 17 May 2016 10:46:00 +0900 
		*/
		String[] s = pubdate.split(" ");
		String year = s[3];
		String month= s[2];
		String date= s[1];
		
		switch (month) {
		case "May":
			month = "5";
			break;

		default:
			break;
		}
		
		String[] str = {year, month, date};
		return str;
	}
	
	public ArrayList<NewsDTO> getdata(String query){
		
		String xml = "";

		ArrayList<NewsDTO> news= new ArrayList<NewsDTO>();
		
		String title = "";
		String pubdate = "";
		String description = "";
		String link = "";

		NaverSearchAPI api = new NaverSearchAPI(query);

		try {
			xml = api.getXML();
			InputSource is = new InputSource(new StringReader(xml));
			Document document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(is);
			XPath xpath = XPathFactory.newInstance().newXPath(); // Xpath 생성

			/* <item> 가져오기 */
			NodeList items = (NodeList) xpath.evaluate("//item", document,
					XPathConstants.NODESET);
		
			// <item>안의 값 뽑아서 페이지에 출력하기
			for (int i = 0; i < items.getLength(); i++) {

				NodeList cols = items.item(i).getChildNodes();
				
				title = cols.item(0).getTextContent();
				link = cols.item(1).getTextContent();
				description = cols.item(3).getTextContent();
				pubdate = cols.item(4).getTextContent();
				
				String[] date = getdate(pubdate);
				
				NewsDTO obj = new NewsDTO(title, link, date, description);
				news.add(obj);

			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return news;

	}

}
