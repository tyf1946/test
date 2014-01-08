package com.dragon.storiesofbj.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.util.Base64;
import android.util.Log;
import android.util.Xml;

public class HttpUtils {

	private static final String TAG = "HttpUtils";

	public static String getData(String uri, String... params) {
		try {
			String urlString = "http://10.0.0.7:8888/CMISWebService301/services/"
					+ uri;
			String soapActionString = "http://cdstm.org/Distance2TheSpot";
			String str = serializer(params);
			String xmlFile = "mnt/sdcard/getInHospital.xml";
			File fileToSend = new File(xmlFile);
			if (!fileToSend.exists()) {
				fileToSend.mkdir();
			}
			FileOutputStream fos = new FileOutputStream(fileToSend);
			fos.write(str.getBytes());
			fos.close();
			URL url = new URL(urlString);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();
			byte[] buf = new byte[(int) fileToSend.length()];
			new FileInputStream(xmlFile).read(buf);
			httpConn.setRequestProperty("Content-Length",
					String.valueOf(buf.length));
			httpConn.setRequestProperty("Content-Type",
					"text/xml; charset=utf-8");
			httpConn.setRequestProperty("soapActionString", soapActionString);
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setConnectTimeout(5000);
			OutputStream out = httpConn.getOutputStream();
			out.write(buf);
			out.close();
			System.setProperty("http.keepAlive", "false");
			InputStream is = httpConn.getInputStream();
			String d = readBufferedReader(is);
			System.out.println("=========================");
			System.out.println("--" + d);
			String a = getXml(is);
			System.out.println("++" + a);
			System.out.println(unStringGZip(a));
			return unStringGZip(d);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getXml(InputStream is) {
		String returnString = "";
		try {
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			parser.setInput(is, "utf-8");
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_TAG:
					String start = parser.getName();
					if ("ns1:out".equals(start)) {
						returnString = parser.nextText();
						Log.i(TAG, returnString);
					}
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnString;
	}

	/**
	 * ���������ж�ȡ���
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static String readBufferedReader(InputStream inStream)
			throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inStream));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String xml = buffer.toString();
		return xml;
	}

	public static String unStringGZip(String str) throws IOException {
		String b = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(str,
				0));
		GZIPInputStream gzip = new GZIPInputStream(bis);
		byte[] buf = new byte[1024];
		int num = -1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while ((num = gzip.read(buf, 0, buf.length)) != -1) {
			baos.write(buf, 0, num);
		}
		b = baos.toString();
		baos.flush();
		baos.close();
		gzip.close();
		bis.close();
		return b;
	}

	public static String serializer(String... params) {
		StringWriter sw = new StringWriter();
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlSerializer serializer = factory.newSerializer();
			serializer.setOutput(sw);
			serializer.startDocument("utf-8", null);
			serializer.startTag(null, "soap:Envelope");
			serializer.attribute(null, "xmlns:xsi",
					"http://www.w3.org/2001/XMLSchema-instance");
			serializer.attribute(null, "xmlns:xsd",
					"http://www.w3.org/2001/XMLSchema");
			serializer.attribute(null, "xmlns:soap",
					"http://schemas.xmlsoap.org/soap/envelope/");
			serializer.startTag(null, "soap:Body");
			serializer.startTag(null, params[0]);
			serializer.attribute(null, "xmlns", "http://WebXml.com.cn/");
			serializer.startTag(null, "pdaCode");
			serializer.text(params[1]);
			serializer.endTag(null, "pdaCode");
			serializer.startTag(null, "deptCode");
			serializer.text(params[2]);
			serializer.endTag(null, "deptCode");

			serializer.endTag(null, params[0]);
			serializer.endTag(null, "soap:Body");
			serializer.endTag(null, "soap:Envelope");
			serializer.endDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
}
