package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Compare title article after screen rotation")
    @Description("We open article Object-oriented programming language and rotate device screen. After check title of article")
    @Step("Starting test testCompareArticleTitle")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testChangeScreenOrientationOnSearchResult(){
        if(Platform.getInstance().isMW()) {
        return;
        }

            SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

            searchPageObject.setSearchSkipButton();
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine("Java");
            searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
            ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

            String title_before_rotation = ArticlePageObject.getArticleTitle();
            this.rotateScreenLandscape();

            String title_after_rotation = ArticlePageObject.getArticleTitle();

            Assert.assertEquals(
                    "Article title have been changed after rotation",
                    title_before_rotation,
                    title_after_rotation
            );
            this.rotateScreenPortrait();
            String title_after_second_rotation = ArticlePageObject.getArticleTitle();
            Assert.assertEquals(
                    "Article title have been changed after rotation",
                    title_before_rotation,
                    title_after_second_rotation
            );
        }


    @Test
    @Feature(value = "Article")
    @DisplayName("Checking article title after app background")
    @Description("We search and open the article after set app to the background(method does nothing for Mobile Web )")
    @Step("Starting test testCheckSearchArticleInBackground (method does nothing for Mobile Web )")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCheckSearchArticleInBackground() {
        if(Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");

        this.backgroundApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");

    }
}
