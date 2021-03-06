/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.persistence;

import com.hp.hpl.jena.ontology.OntModel;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author sandro.emmenegger
 */
public class FileOntAOTest extends AbstractUnitTest{
    
    @Before
    public void loadTestModelset(){
        SimpleModelTransformator.getInstance();
    }
    
    /**
     * Test of getModelSet method, of class OntAO.
     */
    @Test
    public void testGetMetaModelSet() {
        OntModel result = FileOntAO.getInstance().getMetaModel();
//        result.write(System.out, "Turtle");
        assertNotNull(result);
    }
        

    /**
     * Test of getModelSet method, of class OntAO.
     */
//    @Ignore
    @Test
    public void testGetModelSet() {
        OntModel result = FileOntAO.getInstance().getModelSet(MODELSET_ID);
//        result.write(System.out, "Turtle");
        assertNotNull(result);
    }
    
    /**
     * Test of getExecutionData method, of class OntAO.
     */
//    @Ignore
    @Test
    public void testGetExecutionData() {
        OntModel result = FileOntAO.getInstance().getExecutionData(MODELSET_ID);
        assertNotNull(result);
    }    
    
}
