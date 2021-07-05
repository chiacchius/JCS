package it.uniroma2.dicii.isw2.jcs.paramTests;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;


/**
 * Description of the Class
 *
 */
@RunWith(value= Parameterized.class)
public class JCSLightLoadUnitTest {

    private int items;
    private int removeItem;
    private String instance;
    private JCS jcs;
    private String configFileName;

    /**
     * Test setup
     */
    @Before
    public void setUp()
            throws CacheException
    {
        System.out.println("starting setUp");
        JCS.setConfigFilename( configFileName );

        jcs = JCS.getInstance( instance);


        for (int i = 1; i <= items; i++) {
            jcs.put(i + ":key", "data" + i);

        }
    }


    public JCSLightLoadUnitTest(String configFileName, String instance, int items,int removeItem)
    {

        this.configFileName=configFileName;
       this.instance=instance;
       this.items = items;
       this.removeItem = removeItem;
    }



    //input parameters
    @Parameterized.Parameters
    public static Collection<?> getParameters(){
        return Arrays.asList(new Object[][] {

                // String configFileName, String instance, int items,int removeItem
                
                //{null, "testCache0", 100, 3},
                {"/TestSimpleLoad.ccf", "testCache1", 999, 300},
                {"/TestSimpleLoad.ccf", "testCache2", -1, 0},
                {"/TestSimpleLoad.ccf", "testCache3", 0, 1}

        });
    }

    /**
     * A unit test for JUnit
     *
     * @exception Exception
     *                Description of the Exception
     */
    @Test
    public void testSimpleLoad() throws Exception {


        //JCS jcs = JCS.getInstance( "testCache1" );
        //        ICompositeCacheAttributes cattr = jcs.getCacheAttributes();
        //        cattr.setMaxObjects( 20002 );
        //        jcs.setCacheAttributes( cattr );

        System.out.println("starting test");
        if (jcs == null){
            Assert.assertNull(jcs);
            System.out.println("jcs is null");
            return;
        }

        for ( int i = items; i > 0; i-- )
        {

            String res = (String) jcs.get( i + ":key" );

            if ( res == null )
            {
                assertNotNull( "[" + i + ":key] should not be null", res );
            }
        }

        // test removal
        jcs.remove(removeItem + ":key" );
        assertNull( jcs.get(removeItem + ":key" ) );
        System.out.println("test finished");

    }

}
