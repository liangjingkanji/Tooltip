# Tooltip

用于支持各种提示工具

## 安装

project 的 build.gradle

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```



module 的 build.gradle

```groovy
implementation 'com.github.liangjingkanji:Tooltip:1.0'
```





## Toast

### 特性

-   可以子线程异步显示吐司
-   连续弹出吐司不会覆盖, 会自动取消上个吐司显示.



### 显示吐司

```
toast(message)

longToast(message)
```



### 自定义视图

```
fun Context.toast(content: View, config: Toast.(v: View) -> Unit = {})
```



使用

```
toast(contentView){
	
}
```

