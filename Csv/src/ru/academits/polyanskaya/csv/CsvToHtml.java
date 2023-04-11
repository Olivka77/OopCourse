package ru.academits.polyanskaya.csv;

import java.io.*;

public class CsvToHtml {
    public static void main(String[] args) {
        /*String inputCsv = args[0];
        String outputHtml = args[1];

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputCsv));
             PrintWriter writer = new PrintWriter(outputHtml)) {*/
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.csv"));
             PrintWriter writer = new PrintWriter("output.html")) {
            writer.print("<!DOCTYPE html>" + System.lineSeparator() + "<html lang=\"ru\">" + System.lineSeparator() + "\t<head>"
                    + System.lineSeparator() + "\t\t<meta charset=\"UTF-8\">" + System.lineSeparator() + "\t</head>"
                    + System.lineSeparator() + "\t<body>" + System.lineSeparator() + "\t\t<table>" + System.lineSeparator());

            String line;

            boolean isNewLineInDetail = false;
            boolean isDetailInQuotes = false;
            boolean isNewDetail = false;

            while ((line = bufferedReader.readLine()) != null) {
                if (!isNewLineInDetail) {
                    writer.println("\t\t\t<tr>");
                    writer.print("\t\t\t\t<td>");

                    isNewDetail = true;
                }

                char[] chars = line.toCharArray();

                if (line.length() == 0 && !isNewLineInDetail) {
                    throw new Exception("ошибка формата входного файла");
                }

                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == '<') {
                        writer.print("&lt;");
                    } else if (chars[i] == '>') {
                        writer.print("&gt;");
                    } else if (chars[i] == '&') {
                        writer.print("&amp;");
                    } else if ((chars[i] != ',' && chars[i] != '"') || (chars[i] == '"' && isDetailInQuotes) || (chars[i] == ',' && isNewDetail)) {
                        writer.print(chars[i]);
                    } else {
                        if (chars[i] == '"') {
                            isDetailInQuotes = !isDetailInQuotes;

                            if (getQuotesCount(chars, i) % 2 != 0) {
                                //isNewLineInDetail = true;
                            }

                            int currentQuotesCount = 0;

                            while (chars[i] == 'i') {
                                currentQuotesCount++;

                                if (currentQuotesCount % 2 == 0) {
                                    writer.print('\"');
                                }

                                i++;
                            }
                        }

                        if (chars[i] == ',' ) {
                            isNewDetail = !isNewDetail;

                            if (isNewLineInDetail) {
                                writer.print("\t\t\t\t</td>");

                                isNewLineInDetail = false;
                            } else {
                                writer.print("</td>");
                                writer.print(System.lineSeparator() + "\t\t\t\t<td>");
                            }
                        }
                    }


                }

                if (!isNewLineInDetail) {
                    writer.print("</td>");
                    writer.println(System.lineSeparator() + "\t\t\t</tr>");
                } else {
                    writer.print("<br/>");
                    isNewLineInDetail = false;
                }
            }
            writer.print("\t\t</table>" + System.lineSeparator() + "\t</body>" + System.lineSeparator() + "</html>");
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static int getQuotesCount(char[] chars, int startIndex) {
        int quotesCount = 0;

        for (int i = startIndex; i < chars.length; i++) {
            if (chars[i] == '"') {
                quotesCount++;
            }
        }

        return quotesCount;
    }
}