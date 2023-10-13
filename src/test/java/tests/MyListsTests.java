package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObjects;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleArticle();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        ArticlePageObject.addArticleToMyList(name_of_folder);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.navigateUpFromArticle();
        NavigationUI.navigateUpFromArticle();
        NavigationUI.goToSavedArticles();


        MyListsPageObjects MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.clickNotNow();
        MyListsPageObject.openFolderByName(name_of_folder);
        //hardcode потому что локатор неправильный и текста там не будет для заголовка
        MyListsPageObject.swipeArticleToDelete("Java (programming language)");

    }

    @Test
    public void testSaveAndDeleteTwoArticles(){

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        String word_for_search = "Java";
        searchPageObject.typeSearchLine(word_for_search);
        String first_article = "Object-oriented programming language";
        searchPageObject.clickByArticleWithSubstring(first_article);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleArticle();

        String name_of_folder = "Learning programming";

        ArticlePageObject.addArticleToMyList(name_of_folder);
        NavigationUI NavigationUI =NavigationUIFactory.get(driver);
        NavigationUI.navigateUpFromArticle();

        String second_article = "JavaScript";
        searchPageObject.clickByArticleWithSubstring(second_article);
        ArticlePageObject.waitForTitleArticle();
        ArticlePageObject.addArticleToMyOldList();

        MyListsPageObjects MyListPageObjects = MyListsPageObjectFactory.get(driver);
        MyListPageObjects.openFolderByName(name_of_folder);

        NavigationUI.navigateUpFromArticle();
        NavigationUI.navigateUpFromArticle();
        NavigationUI.goToSavedArticles();
        MyListPageObjects.clickNotNow();
        MyListPageObjects.openFolderByName(name_of_folder);
        String first_article_title = "//*[@text = 'Java (programming language)']";
        MyListPageObjects.swipeArticleToDelete(first_article_title);

        searchPageObject.waitForSearchResult(second_article);
        searchPageObject.clickByArticleWithSubstring(second_article);
    }


}
