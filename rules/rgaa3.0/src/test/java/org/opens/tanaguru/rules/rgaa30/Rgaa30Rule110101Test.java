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
package org.opens.tanaguru.rules.rgaa30;

import java.util.LinkedHashSet;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 11.01.01 of the referential Rgaa 3.0.
 *
 */
public class Rgaa30Rule110101Test extends Rgaa30RuleImplementationTestCase {

    public Rgaa30Rule110101Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName( "org.opens.tanaguru.rules.rgaa30.Rgaa30Rule110101");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-07.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-08.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-09.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-10.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-11.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-12.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-13",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-13.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-14",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-14.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-15",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-15.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-1Passed-16",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-1Passed-16.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-2Failed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-2Failed-07.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110101/Rgaa30.Test.11.01.01-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-01");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
//        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-02");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-03");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-04");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-05");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-06");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-07");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-08");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-09");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-10------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-10");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-11------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-11");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-12------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-12");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-13------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-13");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-14------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-14");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-15------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-15");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-16------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-1Passed-16");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-01");
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(RemarkMessageStore.INVALID_FORM_FIELD_MSG, processRemark.getMessageCode());
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-02");
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(RemarkMessageStore.INVALID_FORM_FIELD_MSG, processRemark.getMessageCode());
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-03");
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(RemarkMessageStore.INVALID_FORM_FIELD_MSG, processRemark.getMessageCode());
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-04");
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(RemarkMessageStore.INVALID_FORM_FIELD_MSG, processRemark.getMessageCode());
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-05");
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(RemarkMessageStore.INVALID_FORM_FIELD_MSG, processRemark.getMessageCode());
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-06");
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(RemarkMessageStore.INVALID_FORM_FIELD_MSG, processRemark.getMessageCode());
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(HtmlElementStore.TEXTAREA_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-07");
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
//        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(RemarkMessageStore.INVALID_FORM_FIELD_MSG, processRemark.getMessageCode());
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(HtmlElementStore.SELECT_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-03").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-04").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-05").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-06").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-07").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-08").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-09").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-10").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-11").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-12").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-13").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-14").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-15").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-1Passed-16").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.11.01.01-4NA-01").getValue());
    }

}
