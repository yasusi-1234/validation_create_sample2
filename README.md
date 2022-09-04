# スプリングのバリデーション

スプリングのバリデーションをアノテーションなしに
設定するサンプル

## このコードを書いた理由

今回は、SpringのValidationについてのコードになっているが

例えばControllerクラスにFormクラスを引数に取るメソッドがあって

```
@Controller
public class SampleController{
    
    @GetMapping("/")
    public String getSample(@ModelAttribute SampleForm form){
        return "/index";
    }
    
    @PostMapping("/complete")
    public String postSample(
        @Validated @ModelAttribute SampleForm form,
        BindingResult result){
        if(result.hasErrros()){
        return "/index";
        }
        return "/complete";
    }
} 
public class SampleForm {
    
    @NotBlank
    private String titile;
    private String[] items;
    private String[] categories;
    
    各getter, setter
}
```
このitemsとcategoriesのlengthは必ず同じ要素数になる前提で、
後からitems[n]とcategories[n]の相関チェックを行いたくなった
場合にどうしたらよいかという事で備忘録としてあげました。

要件としては<br>
items[n] = null or blank, categories[n] = null or blank: エラーではない
<br>
items[n] = 値が入っている, categories[n] = null or blank: エラー
<br>
items[n] = null or blank, categories[n] = 値が入っている: エラー
<br>
items[n] = 値が入っている, categories[n] = 値が入っている: エラーではない
<br>
みたいな感じに実装したい場合。

本来であれば
```
public class SampleForm{
    @NotBlank
    private String title;
    private List<@Valid CategoryItem> categoryItems;
    
    各getter, setter
}

@itemとcategoryの相関チェックをする独自のアノテーションを作成
public class CategoryItem{
    private String item;
    private String category;
    
    各getter, setter
}

```
こんな感じの実装になっていれば問題無いかとは思うんだが、
フォームクラスのリファクタリングが出来ない場合もあるかと思う。

何でそんな時にBindingResultのaddErrorメソッドを使って
無理やりエラーをBindingResultクラスに挿入する方法を学習したため
備忘録を残した。

