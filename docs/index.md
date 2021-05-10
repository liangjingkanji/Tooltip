## 使用

<img src="https://i.imgur.com/HzIJeyO.gif" width="250"/>

使用`toast`函数显示吐司
```kotlin
toast("提示")
```

<img src="https://i.imgur.com/tyGGzTV.png" width="200"/>

在任何位置都可以直接使用`toast`

## 显示

### 短时间显示

显示时间较短的吐司, 最常用的

```kotlin
toast(message)
```

### 长时间显示

显示时间稍长的吐司

```kotlin
longToast(message)
```

### 配置吐司

在`toast`回调中配置吐司的内容, 例如显示位置, 视图内容... 然后会自动显示

```kotlin
toast {
    setGravity(Gravity.CENTER, 0, 0) // 设置居中显示
    ProgressBar(this@MainActivity) // 最后一行为返回一个View视图
}
```

### 全局等级显示

例如我可能需要定义一些错误提示(红色背景的吐司)或者正确提示(绿色背景的吐司), 我们可以使用一个`int`常量作为等级标签来表示错误或正确

1) 创建回调

```kotlin
const val TOAST_ERROR = 0

ToastConfig.onLevel { context, msg, level ->
    when (level) {
        TOAST_ERROR -> {
            view = View.inflate(context, R.layout.layout_toast_error, null)
                .apply { tv_msg.text = msg }
        }
    }
}
```

2) 显示

```kotlin
toast("这是一个自定义等级为错误的提示", TOAST_ERROR)
```

## 配置

```kotlin
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ToastConfig.apply {

            // 全局Toast(不包含等级和自定义视图Toast)
            onToast { context: Context, msg: CharSequence ->
                view = View.inflate(context, R.layout.layout_toast_default, null)
                    .apply { tv_msg.text = msg }
            }

            // 等级Toast
            onLevel { context, msg, level ->
                when (level) {
                    TOAST_ERROR -> {
                        view = View.inflate(context, R.layout.layout_toast_error, null)
                            .apply { tv_msg.text = msg }
                    }
                }
            }
        }
    }
}
```