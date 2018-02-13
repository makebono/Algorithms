package com.makebono.algorithms.test;

import java.io.IOException;

import com.makebono.algorithms.computationalgeometry.convexhull.grahamscan.GSDemo;
import com.makebono.algorithms.computationalgeometry.convexhull.jarvismarch.JMDemo;
import com.makebono.algorithms.computationalgeometry.convexhull.quickhull.QHDemo;

/** 
 * @ClassName: TestField 
 * @Description: Test class
 * @author makebono
 * @date 2017年11月16日 上午9:50:03 
 *  
 */
public class TestField {
    public static void main(final String[] args) throws IOException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        QHDemo.show();
        JMDemo.show();
        GSDemo.show();
    }

}
