package com.example.yueshaojun.ioc.util;

import android.content.Context;
import android.util.Log;

import com.example.yueshaojun.ioc.container.Container;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.lang.reflect.Constructor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by yueshaojun on 2018/7/25.
 */

public class Parser {
    public static void parse(Context context) {

        try {
            InputStream inputStream = context.getResources().getAssets().open("container.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            // 获取根节点
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node child = nodeList.item(i);
                Log.i("ysj", "node local name" + child.getNodeName());
                if ("bean".equals(child.getNodeName())) {

                    String keyName = ((Element) child).getAttribute("name");
                    String path = ((Element) child).getAttribute("path");
                    Class childClass = Class.forName(path);
                    Constructor[] constructor = childClass.getConstructors();


                    for (int j = 0; j < constructor.length; j++) {
                        Class<?>[] paramTypes = constructor[j].getParameterTypes();
                        Object[] paramObjectList = new Object[paramTypes.length];

                        for (int f = 0; f < paramTypes.length; f++) {
                            Class paramClass = paramTypes[f];

                            if (Container.get(paramClass) == null) {

                                NodeList childChildNodes = child.getChildNodes();
                                for (int k = 0; k < childChildNodes.getLength(); k++) {

                                    Node childChild = childChildNodes.item(k);
                                    if ("dependence".equals(childChild.getNodeName())) {
                                        String childKeyName = ((Element) childChild).getAttribute("name");
                                        String childPath = ((Element) childChild).getAttribute("path");
                                        Class childChildClass = Class.forName(childPath);
                                        Object childChildValue = childChildClass.newInstance();
                                        paramObjectList[f] = childChildValue;
                                        Container.add(childChildClass, childChildValue);
                                    }
                                }
                            } else {
                                paramObjectList[f] = Container.get(paramClass);
                            }
                        }
                        Object childValue = constructor[j].newInstance(paramObjectList);
                        Container.add(childClass, childValue);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
