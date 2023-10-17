package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Test for articles")
public class ArticleTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Compare title article with expected one")
    @Description("We open article 'bject-oriented programming language' and make sure the title exist")
    @Step("Starting test testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
//ищем поле ввода, пишем Java, нажимаем на выдачу в поиске, проеряем что выдалось на экране с ошибкой
    public void testCompareArticleTitle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();

//        ArticlePageObject.takeScreenshot("article_page");
        Assert.assertEquals(
                "We see unexpected message",
                "bject-oriented programming language",
                article_title
        );

    }
    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Swipe article to the footer")
    @Description("We open the article and swipe it to the footer")
    @Step("Starting test testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleArticle();
        ArticlePageObject.swipeToFooter();


/*//поправить локатор
        MainPageObject.swipeUpToFindElement(
                By.xpath("//android.view.View[@content-desc = 'View article in browser']"),
                "Cannot find the end of the article",
                10
        );*/

    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Open article and check the title")
    @Description("We search article by the word 'Java' and check the search list with using substring")
    @Step("Starting test testOpenArticleAndCheckTitle")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testOpenArticleAndCheckTitle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        String search_line = "Java";
        searchPageObject.typeSearchLine(search_line);
        String article_title = "Java (programming language)";
        searchPageObject.clickByArticleWithSubstring(article_title);
        searchPageObject.assertThereIsResultOfSearch(article_title);
    }

}
