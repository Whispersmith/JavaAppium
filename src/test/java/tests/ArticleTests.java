package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
//ищем поле ввода, пишем Java, нажимаем на выдачу в поиске, проеряем что выдалось на экране с ошибкой
    public void testCompareArticleTitle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected message",
                "Object-oriented programming language",
                article_title
        );

    }
    @Test
    public void testSwipeArticle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");

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
