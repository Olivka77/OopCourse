package ru.academits.polyanskaya.csv;

import javax.imageio.IIOException;
import java.io.*;

public class CsvToHtml {
    public static void main(String[] args) {
        String inputCsv = args[0];
        String outputHtml = args[1];

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputCsv));
             PrintWriter writer = new PrintWriter(outputHtml)) {
            writer.print("<!DOCTYPE html>" + System.lineSeparator() + "<html lang=\"ru\">" + System.lineSeparator() + "\t<head>"
                    + System.lineSeparator() + "\t\t<meta charset=\"UTF-8\">" + System.lineSeparator() + "<\t/head>"
                    + System.lineSeparator() + "\t<body>" + System.lineSeparator() + "\t\t<table>" + System.lineSeparator());

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String line = scanner.nextLine();

                String[] details = line.split(",", -1);

                if (getQuotesCount(details[0]) % 2 == 0) {
                    stringBuilderResult.append("<tr>");
                }

                for (int i = 0; i < details.length; i++) {
                    stringBuilderTemp.delete(0, stringBuilderTemp.length());

                    int quotesCountInDetail = getQuotesCount(details[i]);

                    if (details[i].length() == 0) {
                        stringBuilderResult.append("<td></td>");
                        continue;
                    }

                    boolean hasNewLineInDetail = quotesCountInDetail % 2 != 0;
                    boolean isDetailStartsWithQuote = details[i].startsWith("\"");
                    boolean isDetailEndsWithQuote = details[i].endsWith("\"");

                    if (!(hasNewLineInDetail && isDetailEndsWithQuote)) {
                        stringBuilderTemp.append("<td>");
                    }

                    stringBuilderTemp.append(details[i]);

                    if (isDetailStartsWithQuote && isDetailEndsWithQuote) {
                        stringBuilderTemp.deleteCharAt(stringBuilderTemp.indexOf("\"")).deleteCharAt(stringBuilderTemp.lastIndexOf("\"")); // здесь падает

                        if (quotesCountInDetail > 3) {
                            for (int j = stringBuilderTemp.length() - 1; j > stringBuilderTemp.length() - details[i].length(); j--) {
                                if (stringBuilderTemp.charAt(j) == '"') {
                                    if (stringBuilderTemp.charAt(j - 1) != '"') {
                                        throw new IIOException("Формат файла не соответствует csv");
                                    }

                                    stringBuilderTemp.deleteCharAt(j);
                                    j--;
                                }
                            }
                        }
                    }

                    if (quotesCountInDetail == 1) {
                        stringBuilderTemp.deleteCharAt(stringBuilderTemp.indexOf("\""));
                    }

                    if (!(hasNewLineInDetail && isDetailStartsWithQuote)) {
                        stringBuilderTemp.append("</td>");
                    } else {
                        stringBuilderTemp.append("<br/>");
                    }

                    if (i == details.length - 1) {
                        if (!hasNewLineInDetail || isDetailEndsWithQuote) {
                            stringBuilderTemp.append("</tr>");
                        }
                    }

                    stringBuilderResult.append(stringBuilderTemp);
                }
            }

            writer.print(line);
            writer.print("<\t\t/table>" + System.lineSeparator() + "\t</body>" + System.lineSeparator() + "</html>");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getQuotesCount(String detail) {
        int quotesCount = 0;

        for (int i = 0; i < detail.length(); i++) {
            if (detail.charAt(i) == '"') {
                quotesCount++;
            }
        }

        return quotesCount;
    }
}