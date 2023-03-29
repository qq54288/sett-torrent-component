<div align="center" >
<img src="https://img.gejiba.com/images/00e96b8fafdaa56f3a31a5418c0ff407.png"  />
</div>
<h1 align="center">sett-torrent-component</h1>


<h4 align="center">基于Xsoup、Jsoup、OKhttp3</h4>
<h4 align="center">从sett插件工具中剥离出来的一个磁力搜索工具，支持自定义规则搜索。</h4>
<div align="center">
<img src="https://img.shields.io/badge/version-1.0.1-gree">
<img alt="GitHub release (latest by date including pre-releases)" src="https://img.shields.io/github/v/release/sayokey/sett-torrent-component?include_prereleases">
</div>
<h4 align="center"> <a target='_blank' href='https://github.com/sayokey/sett-torrent-component-rule'>规则仓库</a> · 其他 </h4>


### 开始使用

---

> 在这里你可以查看一些现成的规则配置，你也可以上传到该仓库
> [sett-torrent-component-rule](https://github.com/sayokey/sett-torrent-component-rule)

```shell
# 将代码clone到本地
git clone https://github.com/sayokey/sett-torrent-component.git 

# 安装maven包
maven install

# 打包
maven package
```

- 在这里查阅文档：待编写。

### 演示

![](https://img.gejiba.com/images/4b59ca5857657fe0243494d67b17dac3.png)
![](https://img.gejiba.com/images/14ad78e5bdbb22c36e12c223cd5b74a0.png)
### 接口

----

> swagger 文档
> localhost:port/swagger-ui.html

1. 搜索接口：`localhost:port/magnet/search?keyword=关键字&nowPage=1&site=配置文件中的站点`
2. 详细信息接口：`localhost:port/magnet/detail?detailUrl=详细信息地址&site=配置文件中的站点`
3. 推送迅雷下载：`localhost:port/thunder/addtask?magnet=磁力链接`

### 规则

---

1. 首先你需要更改application.yml中的规则文件路径
2. 编写规则

规则文件是一个json文件，它可以包含多个站点的配置。

|  参数   | 类型  | 说明 |
|  ----  | ----  | ---- |
| site  | String | 站点名称，在调用API时会传入该值用来获取对应站点规则 |
| searchUrl  | String | 搜索地址，支持模板语法 |
| group  | String | xpath，用来获取结果组，例如搜索结果的table或list |
| count  | Integer | 每页数据数量 |
| groupStart  | Integer | 数据从第几个开始匹配，这是为了防止将table表头误匹配而设置的 |
| page  | String | xpath，用来获取总页数 |
| size  | String | xpath，用来匹配group中每个磁链的大小 |
| name  | String | xpath，用来匹配group中每个磁链的名字 |
| update  | String | xpath，用来匹配group中每个磁链的上传时间 |
| magnet  | String | xpath，用来匹配group中每个磁链的地址 |
| detailUrl  | String | xpath，在某些站点中不会再搜索结果中展示磁链链接等信息，他可能在一个详细信息页面，该配置用来获取详细信息的URL地址 |
| detail  | Detail | 该值是用于获取详细信息 |
| proxy  | Proxy | 代理 |

#### Detail

|  参数   | 类型  | 说明 |
|  ----  | ----  | ---- |
| enable  | Boolean | 是否启用 |
| url  | String | 详细信息地址 |
| size  | String | xpath，用来匹配磁链的大小 |
| name  | String | xpath，用来匹配磁链的名字 |
| update  | String | xpath，用来匹配磁链的上传时间 |
| magnet  | String | xpath，用来匹配磁链的地址 |

#### Proxy

|  参数   | 类型  | 说明 |
|  ----  | ----  | ---- |
| enable  | Boolean | 是否启用 |
| host  | String | 代理主机 |
| port  | Integer | 端口号 |

### 注入值

在执行搜索或查看详细信息时，会向部分属性注入值。

|  模板  | 说明 |
|  ----  | ---- |
| ${keyword}  | 会向`searchUrl`中注入由请求中的keyword参数 |
| ${nowPage}  | 会向`searchUrl`中注入由请求中的nowPage参数 |
| ${index}  | 会向`例如搜索结果的数组中动态参数`中注入由请求中的index参数，代表循环中第N个值 |
| ${detailUrl}  | 会向`detail.url`中注入由请求中的查询请求中的detailUrl参数 |

> 这是一个规则的默认模板

```json
[
  {
    "site": "",
    "searchUrl": "",
    "group": "",
    "count" : 0,
    "groupStart": 0,
    "page": "",
    "size" : "",
    "name": "",
    "update": "",
    "magnet": "",
    "detailUrl": "",
    "detail": {
      "enable": false,
      "url": "",
      "size" : "",
      "name": "",
      "update": "",
      "magnet": ""
    },
    "proxy": {
      "host": "",
      "port": 7890,
      "enable": false
    }
  }
]
```


### 技术栈

---

- SpringBoot2
- Xsuop
- Jsoup
- fastjson
- okhttp3


开始使用

首先下载源代码或者打包后的jar包。

注意：如果下载的是源码或者jar包需要手动创建json规则文件，并在 application.yml 中修改规则文件路径。默认规则路径是项目根目录/rule/config.json

你可以在此处下载一些可供使用的规则配置：https://github.com/sayokey/sett-torrent-component-rule


启动项目
浏览器访问 ip:port/swagger-ui.html 即可打开文档页面

工具提供了三个API接口：

搜索接口： ip:port/magnet/search?keyword=关键字&nowPage=1&site=配置文件中的站点
相信信息接口： ip:port/magnet/detail?detailUrl=详细信息地址&site=配置文件中的站点
windows中推送迅雷下载接口： ip:port/thunder/addtask?magnet=磁力链接

环境及依赖

Java 8
SpringBoot 2.5.6
Xsoup 0.3.6
okhttp3 3.3.0
swagger2 2.9.2

下载
