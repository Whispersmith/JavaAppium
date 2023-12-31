package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;


public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";
    private static final String
            login = "Whsppr",
            password = "Whsppr1234";
    @Test
    @Features(value = {@Feature(value = "Saving"),@Feature(value = "Article")})
    @DisplayName("Saving article to list '{name_of_folder}'")
    @Description("We open article 'bject-oriented programming language' and save it to list '{name_of_folder}'")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveFirstArticleToMyList(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleArticle();
        String article_title = ArticlePageObject.getArticleTitle();


        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        }else {
            ArticlePageObject.addArticleToMySaved();
        }
        if(Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleArticle();

            Assert.assertEquals("We are not in the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickToMyLists();
        if(Platform.getInstance().isAndroid()){
           NavigationUI.navigateUpFromArticle();
            NavigationUI.navigateUpFromArticle();
            NavigationUI.goToSavedArticles();
        }


        MyListsPageObjects MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()){
            MyListsPageObject.clickNotNow();
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        //hardcode потому что локатор неправильный и текста там не будет для заголовка
        MyListsPageObject.swipeArticleToDelete("Java (programming language)");

    }

    @Test
    @Features(value = {@Feature(value = "Saving"),@Feature(value = "Article")})
    @DisplayName("Saving two article to list '{name_of_folder}' and delete one")
    @Description("We save two articles to list '{name_of_folder}' and delete the one from it")
    @Step("Starting test testSaveAndDeleteTwoArticles")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveAndDeleteTwoArticles(){

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        String word_for_search = "Java";
        searchPageObject.typeSearchLine(word_for_search);
        String first_article = "bject-oriented programming language";
        searchPageObject.clickByArticleWithSubstring(first_article);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleArticle();
        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        }else {
            ArticlePageObject.addArticleToMySaved();
        }
        if(Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleArticle();

            Assert.assertEquals("We are not in the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI =NavigationUIFactory.get(driver);
        NavigationUI.navigateUpFromArticle();

        String second_article = "JavaScript";
        searchPageObject.clickByArticleWithSubstring(second_article);
        ArticlePageObject.waitForTitleArticle();
        ArticlePageObject.addArticleToMyOldList();

        MyListsPageObjects MyListPageObjects = MyListsPageObjectFactory.get(driver);
        MyListPageObjects.openFolderByName(name_of_folder);

        if(Platform.getInstance().isAndroid()) {
            NavigationUI.navigateUpFromArticle();
            NavigationUI.navigateUpFromArticle();
            NavigationUI.goToSavedArticles();
            MyListPageObjects.clickNotNow();
        }
        MyListPageObjects.openFolderByName(name_of_folder);
        String first_article_title = "//*[@text = 'Java (programming language)']";
        MyListPageObjects.swipeArticleToDelete(first_article_title);

        searchPageObject.waitForSearchResult(second_article);
        searchPageObject.clickByArticleWithSubstring(second_article);
    }


}
