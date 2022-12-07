package com.smy.gutenberggui.view;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        WebClient webClient = new WebClient();

        webClient.getOptions().setThrowExceptionOnScriptError(false);

        HtmlPage page = webClient.getPage("https://www.gutenberg.org/ebooks/" + 19926);

        HtmlTable htmlTable = (HtmlTable) page.getByXPath("/html/body/div[1]/div[1]/div[2]/div[4]/div/div[1]/div/table").get(0);

        
        
        System.out.println(a.getHrefAttribute());

    }
}
