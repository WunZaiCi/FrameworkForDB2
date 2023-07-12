package com.banma.mvc.servlet;

import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 业务控制器配置中心xml解析器
 * 
 * @author WHASSUPMAN
 *
 */
public class ActionXmlParser {
	private HashMap<String, Action> actions;

	public HashMap<String, Action> parse(InputStream is) throws Exception {
		actions = new HashMap<String, Action>();

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(is, new DefaultHandler() {
			private Action action = null;

			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes)
					throws SAXException {
				if ("action".equals(qName)) {
					action = new Action();
					action.setPath(attributes.getValue("path"));
					action.setName(attributes.getValue("name"));
					action.setResults(new HashMap<String, Result>());

				} else if ("result".equals(qName)) {
					String redirect = attributes.getValue("redirect");

					Result result = new Result(attributes.getValue("name"), attributes.getValue("path"),
							redirect == null ? false : Boolean.parseBoolean(redirect));

					action.getResults().put(result.getName(), result);
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				if ("action".equals(qName)) {
					actions.put(action.getPath(), action);
					action = null;
				}
			}

		});

		return actions;//解析的结果
	}

	/**
	 * 对应的是<action>
	 * 
	 * @author WHASSUPMAN
	 *
	 */
	public static class Action {
		private String name;
		private String path;
		private HashMap<String, Result> results;

		@Override
		public String toString() {
			return "Action [name=" + name + ", path=" + path + ", results=" + results + "]";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public HashMap<String, Result> getResults() {
			return results;
		}

		public void setResults(HashMap<String, Result> results) {
			this.results = results;
		}

	}

	/**
	 * 对应的是<result>
	 * 
	 * @author WHASSUPMAN
	 *
	 */
	public static class Result {
		private String name;
		private String path;
		private boolean redirect;

		public Result(String name, String path, boolean redirect) {
			super();
			this.name = name;
			this.path = path;
			this.redirect = redirect;
		}

		@Override
		public String toString() {
			return "Result [name=" + name + ", path=" + path + ", redirect=" + redirect + "]";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public boolean isRedirect() {
			return redirect;
		}

		public void setRedirect(boolean redirect) {
			this.redirect = redirect;
		}
	}
}
