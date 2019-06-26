package eu.europa.esig.dss.web.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import eu.europa.esig.dss.jaxb.detailedreport.XmlDetailedReport;
import eu.europa.esig.dss.jaxb.simplereport.XmlSimpleReport;
import eu.europa.esig.dss.utils.Utils;
import eu.europa.esig.dss.web.config.DSSBeanConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { DSSBeanConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class XSLTServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(XSLTServiceTest.class);

	private static final eu.europa.esig.dss.jaxb.detailedreport.ObjectFactory OF_DETAILED_REPORT = new eu.europa.esig.dss.jaxb.detailedreport.ObjectFactory();
	private static final eu.europa.esig.dss.jaxb.simplereport.ObjectFactory OF_SIMPLE_REPORT = new eu.europa.esig.dss.jaxb.simplereport.ObjectFactory();

	@Autowired
	private XSLTService service;

	@Test
	@SuppressWarnings("unchecked")
	public void generateSimpleReport() throws Exception {
		JAXBContext context = JAXBContext.newInstance(XmlSimpleReport.class.getPackage().getName());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Marshaller marshaller = context.createMarshaller();

		JAXBElement<XmlSimpleReport> unmarshal = (JAXBElement<XmlSimpleReport>) unmarshaller.unmarshal(new File("src/test/resources/simpleReport.xml"));
		assertNotNull(unmarshal);
		XmlSimpleReport simpleReport = unmarshal.getValue();
		assertNotNull(simpleReport);

		StringWriter writer = new StringWriter();
		marshaller.marshal(OF_SIMPLE_REPORT.createSimpleReport(simpleReport), writer);

		String htmlSimpleReport = service.generateSimpleReport(writer.toString());
		assertTrue(Utils.isStringNotEmpty(htmlSimpleReport));
		LOG.debug("Simple report html : " + htmlSimpleReport);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void generateSimpleReportMulti() throws Exception {
		JAXBContext context = JAXBContext.newInstance(XmlSimpleReport.class.getPackage().getName());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Marshaller marshaller = context.createMarshaller();

		JAXBElement<XmlSimpleReport> unmarshal = (JAXBElement<XmlSimpleReport>) unmarshaller
				.unmarshal(new File("src/test/resources/simple-report-multi-signatures.xml"));
		assertNotNull(unmarshal);
		XmlSimpleReport simpleReport = unmarshal.getValue();
		assertNotNull(simpleReport);

		StringWriter writer = new StringWriter();
		marshaller.marshal(OF_SIMPLE_REPORT.createSimpleReport(simpleReport), writer);

		String htmlSimpleReport = service.generateSimpleReport(writer.toString());
		assertTrue(Utils.isStringNotEmpty(htmlSimpleReport));
		LOG.debug("Simple report html : " + htmlSimpleReport);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void generateDetailedReport() throws Exception {
		JAXBContext context = JAXBContext.newInstance(XmlDetailedReport.class.getPackage().getName());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Marshaller marshaller = context.createMarshaller();

		JAXBElement<XmlDetailedReport> unmarshal = (JAXBElement<XmlDetailedReport>) unmarshaller.unmarshal(new File("src/test/resources/detailedReport.xml"));
		assertNotNull(unmarshal);
		XmlDetailedReport detailedReport = unmarshal.getValue();
		assertNotNull(detailedReport);

		StringWriter writer = new StringWriter();
		marshaller.marshal(OF_DETAILED_REPORT.createDetailedReport(detailedReport), writer);

		String htmlDetailedReport = service.generateDetailedReport(writer.toString());
		assertTrue(Utils.isStringNotEmpty(htmlDetailedReport));
		LOG.debug("Detailed report html : " + htmlDetailedReport);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void generateDetailedReportMultiSignatures() throws Exception {
		JAXBContext context = JAXBContext.newInstance(XmlDetailedReport.class.getPackage().getName());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Marshaller marshaller = context.createMarshaller();

		JAXBElement<XmlDetailedReport> unmarshal = (JAXBElement<XmlDetailedReport>) unmarshaller
				.unmarshal(new File("src/test/resources/detailed-report-multi-signatures.xml"));
		assertNotNull(unmarshal);
		XmlDetailedReport detailedReport = unmarshal.getValue();
		assertNotNull(detailedReport);

		StringWriter writer = new StringWriter();
		marshaller.marshal(OF_DETAILED_REPORT.createDetailedReport(detailedReport), writer);

		String htmlDetailedReport = service.generateDetailedReport(writer.toString());
		assertTrue(Utils.isStringNotEmpty(htmlDetailedReport));
		LOG.debug("Detailed report html : " + htmlDetailedReport);
	}

}
