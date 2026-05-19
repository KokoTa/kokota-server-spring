# 学习笔记

## Mysql

### 类型推荐

| 场景 | 推荐类型 | 原因 |
| ------ | --------- | ------ |
| 主键ID | `BIGINT` | 防止溢出 |
| 状态字段 | `TINYINT` | 值范围小，省空间 |
| 金额 | `DECIMAL(10,2)` | 精度不丢失 |
| 手机号 | `CHAR(11)` | 长度固定 |
| 文章内容 | `TEXT` | 长度不固定且较大 |
| 创建时间 | `DATETIME` | 范围大，不受时区影响 |
| 更新时间 | `TIMESTAMP` | 可自动更新 |

* 整数用 `INT/BIGINT`，小数用 `DECIMAL`，字符串用 `VARCHAR`，时间用 `DATETIME`。
* `TIMESTAMP` 存储的是 UTC 时间，方便了跨时区应用，但是有 2038 年的限制
* Mysql 如果跑在 linux 上的话，其实是大小写敏感的，可以用驼峰命名(虽然规范推荐使用下划线)

## 其他

* `@Autowire` 按类型、`@Resource` 按名称
* `junit` 的 `@Test` 运行测试用例必须添加 `@RunWith(SpringRunner.class)`。见 `MybatisPlusTest.java`

## 文档链接

* [mybatis-plus](https://baomidou.com/getting-started/)
