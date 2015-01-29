package com.joantolos.portfolio.template.utils;

import com.joantolos.portfolio.template.exception.StringManipulationException;

import java.util.ArrayList;

/**
 *
 * Created by jtolos on 13/01/2015.
 */
public interface StringUtils {

    public String toCamelCase(String s, String delimiter);

    public String toProperCase(String s);

    public String firstLetterLowerCase(String s);

    public ArrayList<String> stringArrayToArrayList(String[] array);

    public String replaceLastChars(String originalString, String replaceString, int endOffset) throws StringManipulationException;
}
