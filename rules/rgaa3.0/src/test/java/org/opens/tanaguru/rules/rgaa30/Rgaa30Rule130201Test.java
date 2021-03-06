/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
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

import java.util.Iterator;
import java.util.LinkedHashSet;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.opens.tanaguru.rules.keystore.AttributeStore;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 13.02.01 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule130201Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa30Rule130201Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.rgaa30.Rgaa30Rule130201");
    }

   @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rgaa30.Test.13.02.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule130201/Rgaa30.Test.13.02.01-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.13.02.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule130201/Rgaa30.Test.13.02.01-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.13.02.01-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule130201/Rgaa30.Test.13.02.01-3NMI-03.html"));
        getWebResourceMap().put("Rgaa30.Test.13.02.01-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule130201/Rgaa30.Test.13.02.01-3NMI-04.html"));
        getWebResourceMap().put("Rgaa30.Test.13.02.01-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule130201/Rgaa30.Test.13.02.01-3NMI-05.html"));
        getWebResourceMap().put("Rgaa30.Test.13.02.01-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule130201/Rgaa30.Test.13.02.01-3NMI-06.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.13.02.01-3NMI-01");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        ProcessRemark processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_JS_PROMPT_A_NEW_WINDOW_MSG, processRemark.getMessageCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.02.01-3NMI-02");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_JS_PROMPT_A_NEW_WINDOW_MSG, processRemark.getMessageCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.02.01-3NMI-03");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_JS_PROMPT_A_NEW_WINDOW_MSG, processRemark.getMessageCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.02.01-3NMI-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.02.01-3NMI-05");
        // check number of elements in the page
        assertEquals(4, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(4, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> iter =  processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        Iterator<EvidenceElement> eIter = sourceCodeRemark.getElementList().iterator();
        EvidenceElement ee = eIter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "My link 1"));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "My link 2"));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "My link 3"));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("Link 3 title", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "My link 4"));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.02.01-3NMI-06");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_JS_PROMPT_A_NEW_WINDOW_MSG, processRemark.getMessageCode());
 
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.13.02.01-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.13.02.01-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.13.02.01-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.13.02.01-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.13.02.01-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.13.02.01-3NMI-06").getValue());
    }

}
