package com.p2p.webapp.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;



/**
 * 属性复制
 * 1.参考了  apache BeanUtilsBean 和  spring 的 BeanUtils 两个类
 * 2.只针对  BaseEntity的实现类的 jdk基本类型进行复制
 * 
 * Java的基本类型有八种：int, double, float, long, short, boolean, byte, char， void
 * @author LIZHENZHONG eoems@sina.com
 *
 */

public class PropertyCopyer {}
