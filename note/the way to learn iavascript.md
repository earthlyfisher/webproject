#闭包
闭包是js的一大特点也是一大难点，闭包从变量作用域着手，来完成两件事情：

1 可以读取函数内部的变量
2 让这些变量的值始终保持在内存中

以下是一段比较有能体现js闭包作用域和使用的代码：
```javascript
var name = "The Window";
var object = {
		name : "My Object",
		getNameFunc : function() {
			var that=this;//此this是第一层object.getNameFunc()时的this，即为object
			return function() {
				alert(that.name);
				alert(this.name);//此this是第二层object.getNameFunc()()时的this，
				                 //由于object.getNameFunc()返回的一个匿名函数,是一个全局变量，所以，此时的this是全局变量
				return this.name;
			};
		}
	};
object.getNameFunc()();
```
[学习Javascript闭包(Closure)](http://www.ruanyifeng.com/blog/2009/08/learning_javascript_closures.html)
