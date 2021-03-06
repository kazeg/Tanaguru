/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.opens.tanaguru.service;

import java.util.*;
import junit.framework.TestCase;
import org.apache.http.HttpStatus;
import org.opens.tanaguru.crawler.CrawlerFactory;
import org.opens.tanaguru.crawler.CrawlerFactoryImpl;
import org.opens.tanaguru.crawler.util.*;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.AuditFactory;
import org.opens.tanaguru.entity.factory.audit.AuditFactoryImpl;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.factory.audit.ContentFactoryImpl;
import org.opens.tanaguru.entity.parameterization.*;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.service.mock.MockAuditDataService;
import org.opens.tanaguru.service.mock.MockContentDataService;
import org.opens.tanaguru.service.mock.MockWebResourceDataService;

/**
 * This class is a functionnal test class of the crawler service.
 *
 * @author jkowalczyk
 */
public class CrawlerServiceImplTest extends TestCase {

    private static final String FULL_SITE_CRAWL_URL_KEY = "full-site-crawl-url";
    private static final String ROBOTS_RESTRICTED_CRAWL_URL_KEY =
            "robots-restricted-crawl-url";
    private static final String SITES_URL_BUNDLE_NAME = "sites-url";
    private static final String FULL_SITE_CRAWL_CONF_FILE_PATH =
            "src/test/resources/full-site-crawl-conf/";
    private static final String PAGE_CRAWL_CONF_FILE_PATH =
            "src/test/resources/page-crawl-conf/";
    private static final String PAGE_NAME_LEVEL1 = "page-1.html";
    private static final String PAGE_NAME_LEVEL2 = "page-2.html";
    private static final String FORBIDDEN_PAGE_NAME = "page-access-forbidden-for-robots.html";
    private final ResourceBundle bundle =
            ResourceBundle.getBundle(SITES_URL_BUNDLE_NAME);
    private CrawlerService crawlerService;
    private CrawlerFactory crawlerFactory;
    private AuditFactory auditFactory;
    private ContentFactory contentFactory;
    private WebResourceDataService mockWebResourceDataService;
    private ContentDataService mockContentDataService;
    private AuditDataService mockAuditDataService;
    CrawlConfigurationUtils ccu = CrawlConfigurationUtils.getInstance();

    public CrawlerServiceImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        contentFactory = new ContentFactoryImpl();

        mockWebResourceDataService = new MockWebResourceDataService();
        mockContentDataService = new MockContentDataService();
        mockAuditDataService = new MockAuditDataService();

        crawlerFactory = new CrawlerFactoryImpl();
        crawlerFactory.setOutputDir("/tmp");
        ((CrawlerFactoryImpl) crawlerFactory).setWebResourceDataService(mockWebResourceDataService);
        ((CrawlerFactoryImpl) crawlerFactory).setContentDataService(mockContentDataService);
        ((CrawlerFactoryImpl) crawlerFactory).setContentFactory(contentFactory);

        crawlerService = new CrawlerServiceImpl();
        crawlerService.setCrawlerFactory(crawlerFactory);
        crawlerService.setWebResourceDataService(mockWebResourceDataService);
        crawlerService.setAuditDataService(mockAuditDataService);
        crawlerService.setContentDataService(mockContentDataService);

        auditFactory = new AuditFactoryImpl();

        initCrawlConfigUtils();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     *
     * @param siteUrl
     * @param depth
     * @param regexp
     * @param maxDuration
     * @param maxDocuments
     * @param proxyHost
     * @param proxyPort
     * @return
     */
    private List<Content> initialiseAndLaunchCrawl(
            String siteUrl,
            String depth,
            String exlusionRegexp,
            String inlusionRegexp,
            String maxDuration,
            String maxDocuments,
            String proxyHost,
            String proxyPort) {
        Audit audit = auditFactory.create();
        audit.setParameterSet(setCrawlParameters(depth, exlusionRegexp, inlusionRegexp, maxDuration, maxDocuments, proxyHost, proxyPort));
        WebResource site = crawlerService.crawlSite(audit, siteUrl);
        Collection<Long> contentListId = mockContentDataService.getSSPIdsFromWebResource(site.getId(), HttpStatus.SC_OK, 0, 10);
        List<Content> contentList = new ArrayList();
        for (Long id : contentListId) {
            Content content = mockContentDataService.readWithRelatedContent(id, false);
            if (content != null) {
                System.out.println(content.getURI() + "  " + content.getClass());
                contentList.add(content);
            }
        }
        return contentList;
    }

    public void testCrawl_SiteWithDepthLevel0Option() {
        System.out.println("crawl_full_site_With_Depth_Level0_Option");
        crawlerFactory.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "0", "", "", "", "", "", "");
        assertEquals(1, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
    }

    public void testCrawl_SiteWithDepthLevel1Option() {
        System.out.println("crawl_full_site_With_Depth_Level1_Option");
        crawlerFactory.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "1", "", "", "", "", "", "");
        assertEquals(3, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL1));
        assertTrue(urlSet.contains(siteUrl + FORBIDDEN_PAGE_NAME));
    }

    public void testCrawl_SiteWithRegexpExclusionOption() {
        System.out.println("crawl_full_site_With_Regexp_Exclusion_Option");
        crawlerFactory.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "4", ".html", "", "", "", "", "");
        assertEquals(1, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
    }

    public void testCrawl_SiteWithRegexpInclusionOption() {
        System.out.println("crawl_full_site_With_Regexp_Inclusion_Option");
        crawlerFactory.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY)+"page-1.html";
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "2", "", "page-", "", "10", "", "");
        assertEquals(3, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(bundle.getString(FULL_SITE_CRAWL_URL_KEY) + PAGE_NAME_LEVEL2));
        assertTrue(urlSet.contains(bundle.getString(FULL_SITE_CRAWL_URL_KEY) + FORBIDDEN_PAGE_NAME));
    }

    public void testCrawl_SiteWithRegexpInclusionOption2() {
        System.out.println("crawl_full_site_With_Regexp_Inclusion_Option 2");
        crawlerFactory.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY)+"page-1.html";
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "2", "", "page-\\d", "", "10", "", "");
        assertEquals(2, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(bundle.getString(FULL_SITE_CRAWL_URL_KEY) + PAGE_NAME_LEVEL2));
    }

    public void testCrawl_SiteWithRegexpInclusionOption3() {
        System.out.println("crawl_full_site_With_Regexp_Inclusion_Option 3");
        crawlerFactory.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "2", "", "page-\\d", "", "10", "", "");
        assertEquals(3, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL1));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL2));
    }

    public void testCrawl_SiteWithRegexpExclusionOption2() {
        System.out.println("crawl_full_site_With_Regexp_Exclusion_Option2");
        crawlerFactory.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "4", "robot", "", "", "", "", "");
        assertEquals(3, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL1));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL2));
    }

    public void testCrawl_SiteWithRegexpExclusionOption3() {
        System.out.println("crawl_full_site_With_Regexp_Exclusion_Option3");
        crawlerFactory.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "4", "robot;page-2", "", "", "", "", "");
        assertEquals(2, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL1));
    }

    /**
     * * Test the crawl of a site without robots.txt file
     */
    public void testCrawl_Site() {
        System.out.println("crawl_full_site");
        crawlerFactory.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "3", "", "", "", "", "", "");
        assertEquals(4, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL1));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL2));
        assertTrue(urlSet.contains(siteUrl + FORBIDDEN_PAGE_NAME));
    }

    /**
     * Test the crawl of a page
     */
    public void testCrawl_Page() {
        System.out.println("crawl_page");
        crawlerFactory.setCrawlConfigFilePath(PAGE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        Audit audit = auditFactory.create();
        audit.setParameterSet(setCrawlParameters("3", "", "", "", "", "", ""));
        WebResource page = crawlerService.crawlPage(audit, siteUrl);
        Collection<Long> contentListId = mockContentDataService.getSSPIdsFromWebResource(page.getId(), HttpStatus.SC_OK, 0, 10);
        List<Content> contentList = new ArrayList<Content>();
        for (Long id : contentListId) {
            contentList.add(mockContentDataService.readWithRelatedContent(id, false));
        }
        assertEquals(1, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertFalse(urlSet.contains(siteUrl + PAGE_NAME_LEVEL1));
        assertFalse(urlSet.contains(siteUrl + PAGE_NAME_LEVEL2));
        assertFalse(urlSet.contains(siteUrl + FORBIDDEN_PAGE_NAME));
    }

    /**
     * Test the crawl of a site with robots.txt file
     */
    public void testCrawl_Site_With_Robots() {
        System.out.println("crawl_site_with_robots");
        crawlerFactory.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(ROBOTS_RESTRICTED_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "3", "", "", "", "", "", "");
        assertEquals(3, contentList.size() + ((SSP) contentList.iterator().next()).getRelatedContentSet().size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL1));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL2));
        assertFalse(urlSet.contains(siteUrl + FORBIDDEN_PAGE_NAME));
    }

    private Set<String> getUrlSet(List<Content> contentList) {
        Set<String> urlSet = new HashSet<String>();
        for (Content content : contentList) {
            urlSet.add(content.getURI());
        }
        return urlSet;
    }

    /**
     *
     * @param depth
     * @param exclusionRegexp
     * @param inclusionRegexp
     * @param maxDuration
     * @param maxDocuments
     * @param proxyHost
     * @param proxyPort
     * @return The set of Parameters regarding options set as argument
     */
    private Set<Parameter> setCrawlParameters(
            String depth,
            String exclusionRegexp,
            String inclusionRegexp,
            String maxDuration,
            String maxDocuments,
            String proxyHost,
            String proxyPort) {
        Set<Parameter> crawlParameters = new HashSet<Parameter>();
        ParameterFamily pf = new ParameterFamilyImpl();
        pf.setParameterFamilyCode("CRAWLER");

        //DEPTH
        ParameterElement ped = new ParameterElementImpl();
        ped.setParameterElementCode("DEPTH");
        Parameter pedValue = new ParameterImpl();
        pedValue.setParameterElement(ped);
        pedValue.setValue(depth);
        crawlParameters.add(pedValue);

        //EXCLUSION_REGEX
        ParameterElement peer = new ParameterElementImpl();
        peer.setParameterElementCode("EXCLUSION_REGEX");
        Parameter peerValue = new ParameterImpl();
        peerValue.setParameterElement(peer);
        peerValue.setValue(exclusionRegexp);
        crawlParameters.add(peerValue);

        //INCLUSION_REGEX
        ParameterElement peir = new ParameterElementImpl();
        peir.setParameterElementCode("INCLUSION_REGEX");
        Parameter peirValue = new ParameterImpl();
        peirValue.setParameterElement(peir);
        peirValue.setValue(inclusionRegexp);
        crawlParameters.add(peirValue);

        //MAX_DURATION
        ParameterElement pemdu = new ParameterElementImpl();
        pemdu.setParameterElementCode("MAX_DURATION");
        Parameter pemduValue = new ParameterImpl();
        pemduValue.setParameterElement(pemdu);
        pemduValue.setValue(maxDuration);
        crawlParameters.add(pemduValue);

        //MAX_DOCUMENTS
        ParameterElement pemdo = new ParameterElementImpl();
        pemdo.setParameterElementCode("MAX_DOCUMENTS");
        Parameter pemdoValue = new ParameterImpl();
        pemdoValue.setParameterElement(pemdo);
        pemdoValue.setValue(maxDocuments);
        crawlParameters.add(pemdoValue);

        //PROXY_HOST
        ParameterElement peph = new ParameterElementImpl();
        peph.setParameterElementCode("PROXY_HOST");
        Parameter pephValue = new ParameterImpl();
        pephValue.setParameterElement(peph);
        pephValue.setValue(proxyHost);
        crawlParameters.add(pephValue);

        //PROXY_PORT
        ParameterElement pept = new ParameterElementImpl();
        pept.setParameterElementCode("PROXY_PORT");
        Parameter peptValue = new ParameterImpl();
        peptValue.setValue(proxyPort);
        peptValue.setParameterElement(pept);
        crawlParameters.add(peptValue);

        return crawlParameters;
    }

    /**
     * Set up the CrawlConfigUtils instance with modifiers
     */
    private void initCrawlConfigUtils() {
        HeritrixParameterValueModifier urlModifier = new HeritrixParameterValueModifier();
        urlModifier.setAttributeName("key");
        urlModifier.setAttributeValue("seeds.textSource.value");
        urlModifier.setIdBeanParent("longerOverrides");
        urlModifier.setElementName("prop");

        ccu.setUrlModifier(urlModifier);

        Map<String, HeritrixConfigurationModifier> modifierMap = new HashMap();

        HeritrixConfigurationModifier depthModifier = new HeritrixAttributeValueModifier();
        depthModifier.setAttributeName("name");
        depthModifier.setAttributeValue("maxHops");
        depthModifier.setIdBeanParent("tooManyHopsDecideRule");
        depthModifier.setElementName("property");
        modifierMap.put("DEPTH", depthModifier);

        HeritrixConfigurationModifier maxDocumentsModifier = new HeritrixAttributeValueModifier();
        maxDocumentsModifier.setAttributeName("name");
        maxDocumentsModifier.setAttributeValue("maxDocumentsDownload");
        maxDocumentsModifier.setIdBeanParent("crawlLimiter");
        maxDocumentsModifier.setElementName("property");
        modifierMap.put("MAX_DOCUMENTS", maxDocumentsModifier);

        HeritrixConfigurationModifier maxDurationModifier = new HeritrixAttributeValueModifier();
        maxDurationModifier.setAttributeName("name");
        maxDurationModifier.setAttributeValue("maxTimeSeconds");
        maxDurationModifier.setIdBeanParent("crawlLimiter");
        maxDurationModifier.setElementName("property");
        modifierMap.put("MAX_DURATION", maxDurationModifier);

        HeritrixConfigurationModifier proxyHostModifier = new HeritrixAttributeValueModifier();
        proxyHostModifier.setAttributeName("name");
        proxyHostModifier.setAttributeValue("httpProxyHost");
        proxyHostModifier.setIdBeanParent("fetchHttp");
        proxyHostModifier.setElementName("property");
        modifierMap.put("PROXY_HOST", proxyHostModifier);

        HeritrixConfigurationModifier proxyPortModifier = new HeritrixAttributeValueModifier();
        proxyPortModifier.setAttributeName("name");
        proxyPortModifier.setAttributeValue("httpProxyPort");
        proxyPortModifier.setIdBeanParent("fetchHttp");
        proxyPortModifier.setElementName("property");
        modifierMap.put("PROXY_PORT", proxyPortModifier);

        HeritrixConfigurationModifier exclusionRegexpModifier = new HeritrixContainsRegexpParameterValueModifier();
        exclusionRegexpModifier.setXpathExpression("//list[ancestor::property/@name='regexList' and ancestor::bean/@id='matchesListRegexDecideRule']");
        exclusionRegexpModifier.setElementName("value");
        modifierMap.put("EXCLUSION_REGEX", exclusionRegexpModifier);

        HeritrixConfigurationModifier inclusionRegexpModifier = new HeritrixContainsRegexpParameterValueModifier();
        inclusionRegexpModifier.setXpathExpression("//list[ancestor::property/@name='regexList' and ancestor::bean/@id='inclusionListRegexDecideRule']");
        inclusionRegexpModifier.setElementName("value");
        modifierMap.put("INCLUSION_REGEX", inclusionRegexpModifier);

        ccu.setParameterModifierMap(modifierMap);
    }
//    /**
//     * Test of crawl method, of class CrawlerServiceImpl.
//     */
//    public void testCrawl_Site_With_SiteMap() {
//        System.out.println("crawl_site_with_sitemap");
//        crawler.setSiteURL(null);
//        CrawlerServiceImpl instance = new CrawlerServiceImpl();
//        Site expResult = null;
//        Site result = instance.crawl(site);
//    }
}