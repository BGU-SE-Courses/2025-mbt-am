/*
 *  This is a good place to put common test data, project-wide constants, etc.
 */

const CHROME_DRIVER_PATH = "C:\\Users\\noash\\OneDrive - post.bgu.ac.il\\Studies\\2025A\\SoftwareQualityEngineering\\projects\\project4\\2025-mbt-am\\Selenium\\chromedriver.exe";

const CREDENTIALS = {
  // Credentials for the admin and user
  ADMIN: {
    USERNAME: "admin",
    PASSWORD: "am1234",
    PRODUCT_NAME: "MacBook",
    PRODUCT_MODEL: "Product 16"
  },
  USER:
    {
      FULL_NAME: "test",
      REVIEW: "1234567890123456789012345",
      RATING: "5"
    }
}

const XPATH_LOCATORS = {
  ADMIN: {
    URL: "http://localhost/opencartsite/admin/",
    USERNAME_BOX: "//*[@id='input-username']",
    PASSWORD_BOX: "//*[@id='input-password']",
    CONTINUE_BUTTON: "//button[1]",
    EXIT_WARNING_BUTTON: "//*[@id=\"modal-security\"]/div/div/div[1]/button",
    CATALOG_BUTTON: "//nav[1]/ul[1]/li[2]/a[1]",
    PRODUCTS_BUTTON: "//*[@id=\"collapse-1\"]/li[2]/a",
    PRODUCT_NAME_BOX: "//*[@id='input-name']",
    PRODUCT_MODEL_BOX: "//*[@id='input-model']",
    FILTER_BUTTON: "//*[@id='button-filter']",
    EDIT_BUTTON: "//td[7]/div[1]/a[1]",
    DATA_TAB_BUTTON: "//form[1]/ul[1]/li[2]/a[1]",
    STATUS_BOX: "//fieldset[4]/div[6]/label[1]",
    STATUS_TOGGLE: "//*[@id='input-status']",
    SAVE_BUTTON: "//div[1]/div[1]/div[1]/button[1]",
    SUCCESS_ALERT: "//div[contains(@class, 'alert-success')]",
    TOP_OF_PAGE: "//header[1]/div[1]"
  },
  USER: {
    URL: "http://localhost/opencartsite",
    REVIEWS_TAB: "//div[2]/div[1]/p[1]/a[1]",
    FIRST_PRODUCT_IN_PAGE: "//main[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]",
    FULL_NAME_BOX: "//*[@id='input-author']",
    REVIEW_TEXT_BOX: "//*[@id='input-text']",
    RATING_SCALE_1: "//*[@id='input-rating']/input[1]",
    RATING_SCALE_2: "//*[@id='input-rating']/input[2]",
    RATING_SCALE_3: "//*[@id='input-rating']/input[3]",
    RATING_SCALE_4: "//*[@id='input-rating']/input[4]",
    RATING_SCALE_5: "//*[@id='input-rating']/input[5]",
    CONTINUE_BUTTON: "//*[@id='button-review']",
    SUCCESS_ALERT: "//div[contains(@class, 'alert-success')]"
  }
}

const session = {admin: "ADMIN_SESSION", user: "USER_SESSION"}; // Session names




/*
 *  This is a good place to put common test data, project-wide constants, etc.
 */

