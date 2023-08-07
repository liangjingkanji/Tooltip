快速创建iOS风格`Dialog`加载对话框

<img src="https://i.loli.net/2021/08/14/8eDp7Oz2CQT9Jcq.gif" width="250"/>

```kotlin
BubbleDialog(this).show() // 使用默认标题
BubbleDialog(this, "正在清除中").show() // 修改标题
```

更新标题
```kotlin
dialog.updateTitle("清除完毕")
```

修改默认标题, 在项目`values`目录的strings.xml添加以下

```kotlin
<string name="bubble_loading_title">请稍等...</string>
```


## 自动化

使用[Net](https://github.com/liangjingkanji/Net)网络请求框架可以实现自动显示/隐藏对话框

### 1. 全局配置

所有`scopeDialog`默认使用全局对话框

```kotlin
NetConfig.initialize("https://github.com/liangjingkanji/Net/", this) {
    setDialogFactory {
        ProgressDialog(it).apply {
            setMessage("我是全局自定义的加载对话框...")
        }
    }
}
```

### 2. 使用

```kotlin
scopeDialog {
    tv_fragment.text = Post<String>("dialog") {
        param("u_name", "drake") // 请求参数
        param("pwd", "123456")
    }.await()
}
```

### 3. 单例配置

指定当前网络请求的加载对话框

```kotlin
val dialog = BubbleDialog(context, "清除缓存中")
scopeDialog(dialog) {
    tv_fragment.text = Post<String>("dialog") {
        param("u_name", "drake") // 请求参数
        param("pwd", "123456")
    }.await()
}
```

