package com.inn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@EnableScheduling
@EnableAsync
public class BasicAuthClient extends TimerTask {
	public static long used_bytes = 0;
	public static String max_bytes = null;
	public static String cpu_load = null;
	public static String threads_count = null;
	public static double system_load_avg = 0;
	public static String active_jdbc_count = null;
	public static String used_jdbc_count = null;
	public static String subject = null;

	public static String to = null;
	public static String from = null;
	public static String host = null;
	public static String port = null;
	public static String ssl = null;
	public static String url = null;
	public static String auth = null;
	public static boolean microserviceEnable = false;

	public static String Meldpass = null;
	public static String Melduser = null;
	public static String path = null;
	public static String max_used_memory_per = "";
	public static String max_thread_count = null;
	public static String system_load = null;
	public static String max_active_jdbc_count = null;
	public static String max_used_jdbc_count = null;
	public static String view_current_req = null;
	public static String view_current_jdbc_count = null;
	public static String password = null;
	public static String MailPass = null;
	public static String sendmail = "true";
	public static boolean status1 = false;
	public static boolean status2 = false;
	public static boolean status3 = false;
	public static boolean status4 = false;
	public static boolean status5 = false;
	public static boolean status = false;
	public static int initTime = 1000;
	public static int gapTime1 = 30000;
	public static Double mem = 0.0;
	public static Double max_mem = 0.0;
	static File file = null;
	public static List mylist = null;
	static FileOutputStream out = null;
	static String Filename = "";
	static String microAppName = "";
	static String ExcludeThreads = "";
	static long ConnectionTimeOut = 3000000;

	@Value("${ConnectionTimeOut}")
	public void setConnectionTimeOut(String ConnectionTimeOut){
		this.ConnectionTimeOut = Long.parseLong(ConnectionTimeOut);
	}

	@Value("${view_current_req}")
	public void setCurrentReq(String view_current_req) {
		this.view_current_req = view_current_req;
	}

	@Value("${view_current_jdbc_count}")
	public void setCurrentJDBCReq(String view_current_jdbc_count) {
		this.view_current_jdbc_count = view_current_jdbc_count;
	}

	@Value("${MeldPassword}")
	public void setMeldPass(String pass) {
		this.Meldpass = pass;
	}
	
	@Value("${MeldUser}")
	public void setMeldUser(String meldUser) {
		this.Melduser = meldUser;
	}
	

	@Value("${sendmail}")
	public void setSendMail(String sendmail) {
		this.sendmail = sendmail;
	}

	@Value("${spring.mail.password}")
	public void setMailPass(String MailPass) {
		this.MailPass = MailPass;
	}

	@Value("${to}")
	public void setTo(String to) {
		this.to = to;
	}

	@Value("${from}")
	public void setFrom(String from) {
		this.from = from;
	}

	@Value("${mail.smtp.auth}")
	public void setAuth(String auth) {
		this.auth = auth;
	}

	@Value("${max_used_memory_per}")
	public void setMaxUsedMemory(String max_used_memory_per) {
		this.max_used_memory_per = max_used_memory_per;
	}

	@Value("${url}")
	public void setUrl(String url) {
		this.url = url;
	}

	@Value("${spring.mail.host}")
	public void setHost(String host) {
		this.host = host;
	}

	@Value("${microserviceEnable}")
	public void setMicroserive(String microserviceEnable) {
		this.microserviceEnable = Boolean.parseBoolean(microserviceEnable);
	}

	@Value("${max_active_jdbc_count}")
	public void setMaxActiveJdbCount(String max_active_jdbc_count) {
		this.max_active_jdbc_count = max_active_jdbc_count;
	}

	@Value("${max_used_jdbc_count}")
	public void setMaxUsedJdbcCount(String max_used_jdbc_count) {
		this.max_used_jdbc_count = max_used_jdbc_count;
	}

	@Value("${spring.mail.port}")
	public void setPort(String port) {
		this.port = port;
	}

	@Value("${ExcludeThreads}")
	public void setExcludeThreads(String ExcludeThreads) {
		this.ExcludeThreads = ExcludeThreads;
	}

	@ConstructorBinding
	BasicAuthClient() {
		this.restTemplate = new RestTemplate();
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Start");
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		BasicAuthClient client1 = new BasicAuthClient(restTemplateBuilder);
		client1.callMethod(client1);
		System.out.println("End");
	}

	public void callMethod(BasicAuthClient client) throws FileNotFoundException, IOException {
		Timer ti = new Timer();
		TimerTask ta = client;
		ti.schedule(ta, initTime, gapTime1);
	}

	private final RestTemplate restTemplate;

	public BasicAuthClient(RestTemplateBuilder restTemplateBuilder) {
		if (ConnectionTimeOut > 60000) {
			restTemplateBuilder.setConnectTimeout(Duration.ofMillis(ConnectionTimeOut));
		} else {
			restTemplateBuilder.setConnectTimeout(Duration.ofMillis(60000));
		}
		restTemplate = restTemplateBuilder.basicAuthentication(Melduser, Meldpass).build();
	}

	static final Properties configFile = new Properties();

	@Override
	public void run() {
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		BasicAuthClient client = new BasicAuthClient(restTemplateBuilder);
		try {
			// If  is true means monitoring env types is Monolithic .
			if (microserviceEnable) {
				client.microserviceEnable();
			} else {
				// If microservice is false means monitoring env types is Microservices .
				String appName = "";
				if (client.invokeProtectedResource(appName)) {
					String msg = "[EXTERNAL] This message comes from an external organization.  \n\n\nDear Admin, This is your Service "
							+ microAppName + " info \n\n" + "		Used Vol = " + used_bytes + " MB \n		Max Vol = " + max_bytes
							+ " \n		Thread Count = " + threads_count + "\n		Avg System Load = "
							+ system_load_avg + "\n		Active JDBC Connections = " + active_jdbc_count
							+ "\n		Used JDBC Connection = " + used_jdbc_count + "\n\n\nPlease Check Asap."
							+ "\n\n\n Check these files for more details.";

					if (sendmail.equalsIgnoreCase("true")) {
						client.mail(msg);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void microserviceEnable() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,
				String.class);
		String applicationName = responseEntity.getBody();
		Document doc1 = Jsoup.parse(applicationName);
		getApplicationName(doc1);
	}

	// here we are calling function according to no of application to no. of threads
	public static void getApplicationName(Document doc1) {
		doc1.getAllElements().get(0).getElementById("chooseApplication").select("table").text();
		Element table = doc1.getElementById("chooseApplication");
		Elements names = table.getElementsByTag("a");

		List<String> list_of_names = new ArrayList<String>();
		String n;
		int m=0;
		for (Element name : names) {

			if (name.text().contains("Application available")) {

				n = name.text().replace("Application available ", "");				
				list_of_names.add(n);
				System.out.println("Application Name : "+list_of_names.get(m));				
				m++;
			}			
		}
		m=0;

		ExecutorService executor = Executors.newFixedThreadPool(list_of_names.size());
		for (int i = 0; i < list_of_names.size(); i++) {
			Runnable worker = new SimpleThreadPool(list_of_names.get(i));
			executor.execute(worker);
		}
		executor.shutdown();
	}

	public boolean invokeProtectedResource(String appName) throws IOException {
		subject = "Disk Monitoring Alert : Service ";
		boolean status = false;
		ResponseEntity<String> responseEntity = null;
		ResponseEntity<String> responseEntity1 = null;
		ResponseEntity<String> responseEntity2 = null;
		ResponseEntity<String> responseEntity3 = null;
		ResponseEntity<String> responseEntity4 = null;
		try {
			if (microserviceEnable) {				
				try{
					responseEntity = restTemplate.getForEntity(url + "?application=" + appName + "&format=prometheus",
							String.class);
					responseEntity1 = restTemplate.getForEntity(url + "?application=" + appName, String.class);
					responseEntity2 = restTemplate.getForEntity(url + "?application=" + appName + "&part=currentRequests",
							String.class);
					responseEntity3 = restTemplate.getForEntity(url + "?application=" + appName + "&part=connections",
							String.class);
					responseEntity4 = restTemplate.getForEntity(url + "?application=" + appName + "&part=threadsDump",
							String.class);
				}catch(RestClientException e) {
					e.printStackTrace();
				}
			} else {
				responseEntity = restTemplate.getForEntity(url + "&format=prometheus", String.class);
				responseEntity1 = restTemplate.getForEntity(url, String.class);
				responseEntity2 = restTemplate.getForEntity(url + "&part=currentRequests", String.class);
				responseEntity3 = restTemplate.getForEntity(url + "&part=connections", String.class);
				responseEntity4 = restTemplate.getForEntity(url + "&part=threadsDump", String.class);
				String[] nam = url.split("=");
				microAppName = nam[1];
			}

			String connect = responseEntity2.getBody();
			String jsondata1 = responseEntity1.getBody();
			String threaddump = responseEntity4.getBody();
			String jdbcconnection = responseEntity3.getBody();
			XSSFWorkbook workbook = new XSSFWorkbook();
			Document doc1 = Jsoup.parse(connect);
			Document doc2 = Jsoup.parse(jdbcconnection);			
			Document doc = Jsoup.parse(jsondata1);			
			String jsondata = responseEntity.getBody();
			String[] lines = jsondata.split("\n");
			for (int x = 0; x < lines.length; x++) {
				int c = lines[x].charAt(0);
				if (c != 35) {
					String[] str = lines[x].split(" ");
					String numberOnly = lines[x].replaceAll("[^0-9]", "");
					
					
					if (str[0].equals("javamelody_memory_max_bytes")) {
						System.out.println(
								str[0] + " = " + Math.round(Double.parseDouble(numberOnly) / 1024 / 1024) + " MB");
						Double d = Double.parseDouble(numberOnly) / 1024 / 1024;
						max_bytes = d.toString() + " MB";
						max_mem = d;
						if (used_bytes > ((Math.round(this.max_mem)) * Integer.parseInt(max_used_memory_per) / 100)) {
							status1 = true;
						}
					} else if (str[0].equals("javamelody_threads_count")) {
						System.out.println(str[0] + " = " + numberOnly);
						threads_count = numberOnly.toString();
					} else if (str[0].equals("javamelody_memory_used_bytes")) {
						System.out.println(str[0] + " = " + Double.parseDouble(numberOnly) / 1024 / 1024 + " MB");
						Double d = Double.parseDouble(numberOnly) / 1024 / 1024;
						used_bytes = Math.round(d);
					} else if (str[0].equals("javamelody_system_load_avg")) {
						System.out.println(str[0] + " = " + Double.parseDouble(numberOnly) / 100);
						system_load_avg = Double.parseDouble(numberOnly) / 100;
					} else if (str[0].equals("javamelody_connections_active_count")) {
						System.out.println(str[0] + " = " + numberOnly);
						active_jdbc_count = numberOnly.toString();
						if (Double.parseDouble(numberOnly) > Double.parseDouble(max_used_jdbc_count)) {
							status2 = true;
						}
					} else if (str[0].equals("javamelody_connections_used_count")) {
						System.out.println(str[0] + " = " + numberOnly);
						used_jdbc_count = numberOnly.toString();
						if (Double.parseDouble(numberOnly) > Double.parseDouble(max_used_jdbc_count)) {
							status3 = true;
						}
					}
				}
			}
			
			getViewCurrentRequest(doc1, workbook);
			getHTTPRequestTable(doc, workbook);
			getThreadTable(doc, workbook);
			getOpenJDBCConnection(doc2, workbook);
			if (microserviceEnable) {
				Filename = appName + ".xlsx";
				out = new FileOutputStream(new File(Filename));
			} else {
				out = new FileOutputStream(new File("Monitoringsheet.xlsx"));
			}
			workbook.write(out);
			out.close();
		
			
			FileWriter fi = new FileWriter("threadDump.txt");
			fi.write(threaddump.toString());
			fi.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (status1 || status2 || status3) {
			status = true;
			gapTime1 = 1800000;
		} else {
			status = false;
			gapTime1 = 300000;
		}
		return status;
	}

	public static void getOpenJDBCConnection(Document doc, XSSFWorkbook workbook) {
		XSSFSheet spreadsheet = workbook.createSheet(" Open JDBC Connections Table");
		XSSFRow row;
		Map<String, Object[]> currentReqData = new TreeMap<String, Object[]>();

		try {
			if (doc.getAllElements().get(0).select("table").select("tbody").select("tr") != null) {
				List<Element> ele3 = doc.getAllElements().get(0).select("table").select("tbody").select("tr");
				currentReqData.put("1",
						new Object[] { "Date and stack trace when opened", "Thread and current stack trace" });
				if (ele3.size() > 10) {
					for (int i = 0; i < ele3.size(); i++) {
						currentReqData.put(Integer.toString(currentReqData.size() + 1), new Object[] {
								ele3.get(i).select("td").get(0).text(), ele3.get(i).select("td").get(1).text() });
					}
					spreadsheet.autoSizeColumn(0);

					Set<String> keyid = currentReqData.keySet();
					CellStyle cs = workbook.createCellStyle();
					cs.setWrapText(true);
					int rowid = 0;
					for (String key : keyid) {

						row = spreadsheet.createRow(rowid++);
						Object[] objectArr = currentReqData.get(key);
						int cellid = 0;

						for (Object obj : objectArr) {
							Cell cell = row.createCell(cellid++);
							cell.setCellValue((String) obj);
							cell.setCellStyle(cs);
						}
					}
					for (int i = 0; i < currentReqData.size(); i++) {
						spreadsheet.autoSizeColumn(i);
					}
				} else if (ele3.isEmpty()) {
					System.out.println("There is no OpenJDBCConnections ");
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public static void getViewCurrentRequest(Document doc, XSSFWorkbook workbook) throws IOException {
		XSSFSheet spreadsheet = workbook.createSheet(" View current requests ");
		XSSFRow row;
		Map<String, Object[]> currentReqData = new TreeMap<String, Object[]>();

		currentReqData.put("1",
				new Object[] { "Threads", "Request", "Elapsed time (ms)", "Mean time (ms)", "Cpu time (ms)",
						"Mean cpu time (ms)", "Hits sql", "Mean hits sql", "Time sql (ms)", "Mean time sql (ms)" });

		try {
			if (doc.getAllElements().get(0).select("table").select("tbody").select("tr") != null) {
				List<Element> ele2 = doc.getAllElements().get(0).select("table").select("tbody").select("tr");
				if (ele2.size() > Integer.parseInt(view_current_jdbc_count)) {
					for (int i = 0; i < ele2.size(); i++) {
						currentReqData.put(Integer.toString(currentReqData.size() + 1),
								new Object[] { ele2.get(i).select("td").get(0).text(),
										ele2.get(i).select("td").get(1).text(), ele2.get(i).select("td").get(2).text(),
										ele2.get(i).select("td").get(3).text(), ele2.get(i).select("td").get(4).text(),
										ele2.get(i).select("td").get(5).text(), ele2.get(i).select("td").get(6).text(),
										ele2.get(i).select("td").get(7).text(), ele2.get(i).select("td").get(8).text(),
										ele2.get(i).select("td").get(9).text(),
										ele2.get(i).select("td").get(10).text() });

					}
					spreadsheet.autoSizeColumn(0);

					Set<String> keyid = currentReqData.keySet();
					CellStyle cs = workbook.createCellStyle();
					cs.setWrapText(true);
					int rowid = 0;
					for (String key : keyid) {

						row = spreadsheet.createRow(rowid++);
						Object[] objectArr = currentReqData.get(key);
						int cellid = 0;

						for (Object obj : objectArr) {
							Cell cell = row.createCell(cellid++);
							cell.setCellValue((String) obj);
							cell.setCellStyle(cs);
						}
					}
					for (int i = 0; i < currentReqData.size(); i++) {
						spreadsheet.autoSizeColumn(i);
					}
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public static void getThreadTable(Document doc, XSSFWorkbook workbook) throws IOException {
		XSSFSheet spreadsheet = workbook.createSheet(" Thread Table RUNNABLE");
		XSSFSheet spreadsheet1 = workbook.createSheet(" Thread Table TIMED_WAITING");
		XSSFRow row;
		XSSFRow row1;
		Map<String, Object[]> ThreadDetails = new TreeMap<String, Object[]>();
		Map<String, Object[]> ThreadDetails1 = new TreeMap<String, Object[]>();

		ThreadDetails.put("1", new Object[] { "Thread", "State", "Cpu time (ms)", "User time (ms)" });
		ThreadDetails1.put("1", new Object[] { "Thread", "State", "Cpu time (ms)", "User time (ms)" });

		try {
			if (doc.getElementById("threads_0").select("table").select("tbody").select("tr") != null) {
				List<Element> ele4 = doc.getElementById("threads_0").select("table").select("tbody").select("tr");
				if (ele4.size() > Integer.parseInt(view_current_req)) {
					for (int i = 0; i < ele4.size(); i++) {
						if (!ele4.get(i).select("td").get(0).text().equals("DestroyJavaVM")) {
							if (ele4.get(i).select("td").get(3).text().equals("TIMED_WAITING")) {
								ThreadDetails1.put(Integer.toString(ThreadDetails1.size() + 1), new Object[] {
										ele4.get(i).select("td").get(0).text().substring(0,
												ele4.get(i).select("td").get(0).text().indexOf(" ")),
										ele4.get(i).select("td").get(3).text(), ele4.get(i).select("td").get(5).text(),
										ele4.get(i).select("td").get(6).text() });
								spreadsheet.autoSizeColumn(ThreadDetails1.size(), true);
							} else if (ele4.get(i).select("td").get(3).text().equals("RUNNABLE")) {
								ThreadDetails.put(Integer.toString(ThreadDetails.size() + 1), new Object[] {
										ele4.get(i).select("td").get(0).text().substring(0,
												ele4.get(i).select("td").get(0).text().indexOf(" ")),
										ele4.get(i).select("td").get(3).text(), ele4.get(i).select("td").get(5).text(),
										ele4.get(i).select("td").get(6).text() });
								spreadsheet.autoSizeColumn(ThreadDetails.size(), true);
							}
						}
					}

					Set<String> keyid = ThreadDetails.keySet();
					int rowid = 0;
					for (String key : keyid) {

						row = spreadsheet.createRow(rowid++);
						Object[] objectArr = ThreadDetails.get(key);
						int cellid = 0;

						for (Object obj : objectArr) {
							Cell cell = row.createCell(cellid++);
							cell.setCellValue((String) obj);
						}
					}
					Set<String> keyid1 = ThreadDetails1.keySet();
					rowid = 0;
					for (String key : keyid1) {
						row1 = spreadsheet1.createRow(rowid++);
						Object[] objectArr1 = ThreadDetails1.get(key);
						int cellid = 0;
						for (Object obj : objectArr1) {
							Cell cell = row1.createCell(cellid++);
							cell.setCellValue((String) obj);
						}
					}

					for (int i = 0; i < ThreadDetails1.size(); i++) {
						spreadsheet1.autoSizeColumn(i);
					}
					for (int i = 0; i < ThreadDetails.size(); i++) {
						spreadsheet.autoSizeColumn(i);
					}
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public static void getHTTPRequestTable(Document doc, XSSFWorkbook workbook) throws IOException {
		String[] myList1 = ExcludeThreads.split(",");
		List myList = new ArrayList();
		int l = 0;
		for (String st : myList1) {
			myList.add(st);
			l++;
		}
		l = 0;
		XSSFSheet spreadsheet = workbook.createSheet(" Http Request Table ");
		XSSFRow row;
		Map<String, Object[]> httpDetails = new TreeMap<String, Object[]>();
		httpDetails.put("1",
				new Object[] { "Request", "Hits", "Mean time (ms)", "Max time (ms)", "Standard deviation",
						"% of cumulative cpu time", "Mean cpu time (ms)", "% of system error", "Mean size (Kb)",
						"Mean hits sql" });

		int k = myList.size();
		int j = 0;

		try {
			if (doc.getElementById("detailshttp").selectFirst("table").select("tbody").select("tr") != null) {
				Elements ele1 = doc.getElementById("detailshttp").selectFirst("table").select("tbody").select("tr");
				if (ele1.size() > 0) {
					for (int i = 0; i < ele1.size(); i++) {
						if (!ele1.get(i).select("td").get(3).select("span").get(0).className().equals("info")) {
							httpDetails.put(Integer.toString(httpDetails.size() + 1), new Object[] {
									ele1.get(i).select("td").get(0).text(), ele1.get(i).select("td").get(1).text(),
									ele1.get(i).select("td").get(3).text(), ele1.get(i).select("td").get(4).text(),
									ele1.get(i).select("td").get(5).text(), ele1.get(i).select("td").get(6).text(),
									ele1.get(i).select("td").get(7).text(), ele1.get(i).select("td").get(8).text(),
									ele1.get(i).select("td").get(9).text(), ele1.get(i).select("td").get(10).text() });
						}
					}

					for (int i = 0; i < httpDetails.size(); i++) {
						for (int k1 = 0; k1 < myList.size(); k1++) {
							String st1 = (String) myList.get(k1);
							if (st1.equals(ele1.get(i).select("td").get(0).text().toString())) {
	//							System.out.println("~~~~~~~~~~Http Req Names : "+ele1.get(i).select("td").get(0).text());
								httpDetails.remove(ele1.get(i).select("td").get(0).text().toString());							
//								System.out.println("Find Http Details : "+httpDetails.get(ele1.get(i).select("td").get(0).text()));
							}
						}
					}

					Set<String> keyid = httpDetails.keySet();
					int rowid = 0;
					for (String key : keyid) {

						row = spreadsheet.createRow(rowid++);
						Object[] objectArr = httpDetails.get(key);
						int cellid = 0;

						for (Object obj : objectArr) {
							Cell cell = row.createCell(cellid++);
							cell.setCellValue((String) obj);
						}
					}

					for (int i = 0; i < httpDetails.size(); i++) {
						spreadsheet.autoSizeColumn(i);
					}
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public boolean mail(String str) {
		String message = str;
		subject = "";
		subject = "Disk Monitoring Alert : Service ";
		if (status1) {						
			subject = subject + " using memory " + Math.round(((used_bytes / max_mem) * 100)) + " % Plz Check";
		} else if (status2) {
			subject = subject + " creating jdbc connections more then limit Plz Check ";
		} else {
			subject = subject + " Information. ";
		}
		sendEmail(message, subject);
		return true;
	}

	public static void sendEmail(String msg, String sub) {

		System.out.println("MSG : " + msg + "\n Sub : " + sub + "\n to : " + to + "\n from : " + from);
		Properties pro = System.getProperties();
		pro.put("mail.smtp.host", host);
		pro.put("mail.smtp.port", port);
		pro.put("mail.smtp.ssl.enable", "false");
		pro.put("mail.smtp.auth", "true");

		MimeMessage m = null;
		if (Boolean.valueOf(auth)) {
			Session se = Session.getInstance(pro, new Authenticator() {
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, MailPass);
				}
			});
			se.setDebug(true);
			m = new MimeMessage(se);
		} else {
			Session se1 = Session.getInstance(pro);
			se1.setDebug(true);
			m = new MimeMessage(se1);
		}

		try {

			Multipart multipart = new MimeMultipart();
			BodyPart bp = new MimeBodyPart();
			bp.setText(msg);
			multipart.addBodyPart(bp);
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			MimeBodyPart messageBodyPart1 = new MimeBodyPart();

			String filePath = "";
			if (microserviceEnable) {
				filePath = Filename;
			} else {
				filePath = "Monitoringsheet.xlsx";
			}
			String theadDumpFilePath = "threadDump.txt";
			File f1 = new File(filePath);
			File f2 = new File(theadDumpFilePath);
			DataSource source = new FileDataSource(filePath);
			DataSource source1 = new FileDataSource(theadDumpFilePath);
			messageBodyPart1.setDataHandler(new DataHandler(source));
			messageBodyPart.setDataHandler(new DataHandler(source1));
			messageBodyPart1.setFileName(f1.getName());
			messageBodyPart.setFileName(f2.getName());
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(messageBodyPart1);

			m.setContent(multipart);

			m.setFrom(new InternetAddress(from));
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(sub);
			Transport.send(m, m.getAllRecipients());
			subject = "";
			System.out.println("Sent Successfull .......................");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}