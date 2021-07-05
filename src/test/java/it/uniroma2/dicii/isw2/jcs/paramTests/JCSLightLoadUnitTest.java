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
    private String instance;
    private JCS jcs;

    /**
     * Test setup
     */
    @Before
    public void setUp()
            throws CacheException
    {
        JCS.setConfigFilename( "/TestSimpleLoad.ccf" );
        jcs = JCS.getInstance( instance);
        jcs.clear();
    }


    public JCSLightLoadUnitTest( String instance, int items )
    {
       this.instance=instance;
       this.items = items;
    }



    //input parameters
    @Parameterized.Parameters
    public static Collection<?> getParameters(){
        return Arrays.asList(new Object[][] {

                // String instance, int items

                {"testCache1", 999},
                {"testCache2", -1},
                {"testCache3", 0}

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

        for ( int i = 1; i <= items; i++ )
        {
            jcs.put( i + ":key", "data" + i );
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
        jcs.remove( "300:key" );
        assertNull( jcs.get( "300:key" ) );

    }

}
