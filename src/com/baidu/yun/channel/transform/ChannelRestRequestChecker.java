package com.baidu.yun.channel.transform;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.model.ChannelRequest;
import com.baidu.yun.core.annotation.CollectionRestrict;
import com.baidu.yun.core.annotation.HttpParamKeyName;
import com.baidu.yun.core.annotation.HttpPathKeyName;
import com.baidu.yun.core.annotation.R;
import com.baidu.yun.core.annotation.RangeRestrict;
import com.baidu.yun.core.annotation.RegexRestrict;
import com.baidu.yun.core.utility.StringUtility;

public class ChannelRestRequestChecker {

    public boolean validate(ChannelRequest request)
            throws ChannelClientException {

        Field[] childField = request.getClass().getDeclaredFields();
        Field[] superFileds = request.getClass().getSuperclass()
                .getDeclaredFields();

        List<Field> fieldList = new LinkedList<Field>();
        fieldList.addAll(Arrays.asList(childField));
        fieldList.addAll(Arrays.asList(superFileds));

        for (Field field : fieldList.toArray(new Field[0])) {
            try {
                field.setAccessible(true);

                if (field.isAnnotationPresent(HttpPathKeyName.class)) {
                    Object obj = field.get(request);
                    if (obj == null) {
                        HttpPathKeyName annotation = field
                                .getAnnotation(HttpPathKeyName.class);
                        if (annotation.param() == R.REQUIRE) {
                            throw new ChannelClientException(field.getName()
                                    + " is null, default require");
                        }
                    }
                }

                if (field.isAnnotationPresent(HttpParamKeyName.class)) {
                    Object obj = field.get(request);
                    if (obj == null) {
                        HttpParamKeyName annotation = field
                                .getAnnotation(HttpParamKeyName.class);
                        if (annotation.param() == R.REQUIRE) {
                            throw new ChannelClientException(field.getName()
                                    + " is null, default require");
                        }
                    } else {
                        HttpParamKeyName annotation = field
                                .getAnnotation(HttpParamKeyName.class);
                        Class zlass = field.getType();
                        if (zlass.equals(Long.class)
                                || "long".equalsIgnoreCase(zlass.getName())) {
                            if (obj == null) { // (Long)obj < 0
                                if (annotation.param() == R.REQUIRE) {
                                    throw new ChannelClientException(
                                            field.getName()
                                                    + " haven't been set, default require");
                                }
                            } else {
                                long ele = (Long) obj;
                                if (field
                                        .isAnnotationPresent(RangeRestrict.class)) {
                                    RangeRestrict range = field
                                            .getAnnotation(RangeRestrict.class);
                                    if (ele < range.minLength()
                                            || ele > range.maxLength()) {
                                        throw new ChannelClientException(
                                                field.getName()
                                                        + " value isn't in range ["
                                                        + range.minLength()
                                                        + ", "
                                                        + range.maxLength()
                                                        + "].");
                                    }
                                }
                            }
                        } else if (zlass.equals(Integer.class)
                                || "int".equalsIgnoreCase(zlass.getName())) {
                            if (obj == null) { // (Integer)obj < 0
                                if (annotation.param() == R.REQUIRE) {
                                    throw new ChannelClientException(
                                            field.getName()
                                                    + " haven't been set, default require");
                                }
                            } else {
                                int ele = (Integer) obj;
                                if (field
                                        .isAnnotationPresent(RangeRestrict.class)) {
                                    RangeRestrict range = field
                                            .getAnnotation(RangeRestrict.class);
                                    if (ele < range.minLength()
                                            || ele > range.maxLength()) {
                                        throw new ChannelClientException(
                                                field.getName()
                                                        + " value isn't in range ["
                                                        + range.minLength()
                                                        + ", "
                                                        + range.maxLength()
                                                        + "].");
                                    }
                                }
                            }
                        } else if (zlass.equals(String.class)) {
                            if (StringUtility.isNull((String) obj)) {
                                throw new ChannelClientException(
                                        field.getName()
                                                + " is Zero Length String");
                            } else {
                                String sobj = (String) obj;
                                // String len restrict
                                if (field
                                        .isAnnotationPresent(RangeRestrict.class)) {
                                    RangeRestrict range = field
                                            .getAnnotation(RangeRestrict.class);
                                    if (sobj.length() < range.minLength()
                                            || sobj.length() > range
                                                    .maxLength()) {
                                        throw new ChannelClientException(
                                                field.getName()
                                                        + " length isn't in range ["
                                                        + range.minLength()
                                                        + ", "
                                                        + range.maxLength()
                                                        + "].");
                                    }
                                }
                                // regex restrict
                                if (field
                                        .isAnnotationPresent(RegexRestrict.class)) {
                                    RegexRestrict regex = field
                                            .getAnnotation(RegexRestrict.class);
                                    if (!Pattern.matches(regex.regex(), sobj)) {
                                        throw new ChannelClientException(
                                                field.getName()
                                                        + " doesn't meet the regular expression "
                                                        + regex.regex());
                                    }
                                }
                            }
                        } else if (zlass.equals(java.util.Date.class)) {

                        } else if (zlass.isEnum()) {

                        } else if (zlass.equals(java.util.List.class)) {

                            java.util.List<Object> list = (java.util.List<Object>) obj;
                            if (list.size() == 0) {
                                // throw new Exception();
                                throw new ChannelClientException(
                                        field.getName() + " size is zero.");
                            }
                            if (field
                                    .isAnnotationPresent(CollectionRestrict.class)) {
                                CollectionRestrict rcollection = field
                                        .getAnnotation(CollectionRestrict.class);
                                if (list.size() < rcollection.minLength()
                                        || list.size() > rcollection
                                                .maxLength()) {
                                    throw new ChannelClientException(
                                            field.getName()
                                                    + " size isn't in range ["
                                                    + rcollection.minLength()
                                                    + ", "
                                                    + rcollection.maxLength()
                                                    + "].");
                                }
                            }

                            for (Object o : list) {

                                if (o instanceof String) {
                                    String ele = (String) o;
                                    if (field
                                            .isAnnotationPresent(RangeRestrict.class)) {
                                        RangeRestrict range = field
                                                .getAnnotation(RangeRestrict.class);
                                        if (ele.length() < range.minLength()
                                                || ele.length() > range
                                                        .maxLength()) {
                                            throw new ChannelClientException(
                                                    field.getName()
                                                            + " length isn't in range ["
                                                            + range.minLength()
                                                            + ", "
                                                            + range.maxLength()
                                                            + "].");
                                        }
                                    }
                                    // regex restrict
                                    if (field
                                            .isAnnotationPresent(RegexRestrict.class)) {
                                        RegexRestrict regex = field
                                                .getAnnotation(RegexRestrict.class);
                                        if (!Pattern
                                                .matches(regex.regex(), ele)) {
                                            throw new ChannelClientException(
                                                    field.getName()
                                                            + " doesn't meet the regular expression "
                                                            + regex.regex());
                                        }
                                    }
                                } else if (o instanceof Integer) {
                                    // do nothing
                                    int ele = (Integer) o;
                                    if (field
                                            .isAnnotationPresent(RangeRestrict.class)) {
                                        RangeRestrict range = field
                                                .getAnnotation(RangeRestrict.class);
                                        if (ele < range.minLength()
                                                || ele > range.maxLength()) {
                                            throw new ChannelClientException(
                                                    field.getName()
                                                            + " value isn't in range ["
                                                            + range.minLength()
                                                            + ", "
                                                            + range.maxLength()
                                                            + "].");
                                        }
                                    }
                                } else if (o instanceof Long) {
                                    // do nothing
                                    long ele = (Long) o;
                                    if (field
                                            .isAnnotationPresent(RangeRestrict.class)) {
                                        RangeRestrict range = field
                                                .getAnnotation(RangeRestrict.class);
                                        if (ele < range.minLength()
                                                || ele > range.maxLength()) {
                                            throw new ChannelClientException(
                                                    field.getName()
                                                            + " value isn't in range ["
                                                            + range.minLength()
                                                            + ", "
                                                            + range.maxLength()
                                                            + "].");
                                        }
                                    }
                                }

                            }

                        } else if (zlass.equals(java.util.Set.class)) {
                            java.util.Set<String> set = (java.util.Set<String>) obj;
                            if (set.size() == 0) {
                                // throw new Exception();
                                throw new ChannelClientException(
                                        field.getName() + " size is zero.");
                            }

                            if (field
                                    .isAnnotationPresent(CollectionRestrict.class)) {
                                CollectionRestrict rcollection = field
                                        .getAnnotation(CollectionRestrict.class);
                                if (set.size() < rcollection.minLength()
                                        || set.size() > rcollection.maxLength()) {
                                    throw new ChannelClientException(
                                            field.getName()
                                                    + " size isn't in range ["
                                                    + rcollection.minLength()
                                                    + ", "
                                                    + rcollection.maxLength()
                                                    + "].");
                                }
                            }

                            for (String ele : set) {
                                if (field
                                        .isAnnotationPresent(RangeRestrict.class)) {
                                    RangeRestrict range = field
                                            .getAnnotation(RangeRestrict.class);
                                    if (ele.length() < range.minLength()
                                            || ele.length() > range.maxLength()) {
                                        throw new ChannelClientException(
                                                field.getName()
                                                        + " length isn't in range ["
                                                        + range.minLength()
                                                        + ", "
                                                        + range.maxLength()
                                                        + "].");
                                    }
                                }
                                // regex restrict
                                if (field
                                        .isAnnotationPresent(RegexRestrict.class)) {
                                    RegexRestrict regex = field
                                            .getAnnotation(RegexRestrict.class);
                                    if (!Pattern.matches(regex.regex(), ele)) {
                                        throw new ChannelClientException(
                                                field.getName()
                                                        + " doesn't meet the regular expression "
                                                        + regex.regex());
                                    }
                                }
                            }

                        } else {
                            // throw new Exception();
                            throw new ChannelClientException("");
                        }
                    }
                }

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
