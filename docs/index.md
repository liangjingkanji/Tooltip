## 使用

特点

1. 可子线程
2. 连续显示不重叠
3. 全局自定义
4. 居中屏幕显示

<img src="https://i.loli.net/2021/08/14/R5Gg8kKBeTft9PL.gif" width="250"/>

直接`toast`显示吐司
```kotlin
toast("提示")
```

## 显示

=== "短时间显示"
    ```kotlin
    toast(message)
    ```

=== "长时间显示"
    ```kotlin
    longToast(message)
    ```

## 居中显示吐司

Android12 以后Toast被限制, 居中显示要求自定义吐司视图

1. 文字吐司设置Gravity无效, 但是允许应用后台显示
1. 自定义吐司下Gravity有效, 但是不允许应用后台显示

```kotlin
ToastConfig.initialize(this, ToastGravityFactory()) // 屏幕居中显示吐司
```

## 构建吐司工厂

吐司初始化时可指定`ToastFactory`来自定义全局吐司样式

```kotlin
ToastConfig.initialize(this, ToastGravityFactory())
```

??? 居中显示吐司源码示例
    ```kotlin title="ToastGravityFactory.kt"
    /**
     * 屏幕居中显示吐司
     * @param gravity 对齐方式
     * @param layout 吐司布局
     * @param xOffset x轴偏移量
     * @param yOffset y轴偏移量
     */
    open class ToastGravityFactory(
        val gravity: Int = Gravity.CENTER,
        @LayoutRes val layout: Int = R.layout.layout_gravity_toast,
        val xOffset: Int = 0,
        val yOffset: Int = 0,
    ) : ToastFactory {

        /**
         * 创建吐司
         * @param context Application
         * @param message 吐司内容
         * @param tag 吐司标签
         */
        override fun onCreate(
            context: Application,
            message: CharSequence,
            duration: Int,
            tag: Any?
        ): Toast? {
            val toast = Toast.makeText(context, message, duration)
            val view = View.inflate(context, layout, null)
            view.findViewById<TextView>(android.R.id.message).text = message
            toast.view = view
            toast.setGravity(gravity, xOffset, yOffset)
            return toast
        }
    }
    ```