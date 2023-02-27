package ru.academits.polyanskaya.csv;

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Csv {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream("input.csv"));
             PrintWriter writer = new PrintWriter("output.html")) {
            writer.print("<html><table>");

            StringBuilder stringBuilderResult = new StringBuilder();
            StringBuilder stringBuilderTemp = new StringBuilder();

            while (scanner.hasNextLine()) {
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

                    boolean newLineInDetail = quotesCountInDetail % 2 != 0;
                    boolean detailStartsWithQuote = details[i].startsWith("\"");
                    boolean detailEndsWithQuote = details[i].endsWith("\"");

                    if (!(newLineInDetail && detailEndsWithQuote)) {
                        stringBuilderTemp.append("<td>");
                    }

                    stringBuilderTemp.append(details[i]);

                    if (detailStartsWithQuote && detailEndsWithQuote) {
                        stringBuilderTemp.deleteCharAt(stringBuilderTemp.indexOf("\"")).deleteCharAt(stringBuilderTemp.lastIndexOf("\""));

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

                    if (!(newLineInDetail && detailStartsWithQuote)) {
                        stringBuilderTemp.append("</td>");
                    } else {
                        stringBuilderTemp.append("<br/>");
                    }

                    if (i == details.length - 1) {
                        if (!newLineInDetail || detailEndsWithQuote) {
                            stringBuilderTemp.append("</tr>");
                        }
                    }

                    stringBuilderResult.append(stringBuilderTemp);
                }
            }

            writer.print(stringBuilderResult);
            writer.print("</table></html>");
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден");
        } catch (IIOException e) {
            System.out.println("Формат файла не соответствует формату csv");
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