CatLoading
Step
Import this project into Android Studio... it's built with it.
Usage
Gradle
implementation 'com.github.ahmed2146:CatLoadingTest:v6.5'
 How to use:
 CatLoadingView cview;
cview = new CatLoadingView();
        cview.setClickCancelAble(true);
        cview.setText("Loading, Please wait... ");
cview.show(getSupportFragmentManager(),""); // to show
