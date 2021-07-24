#简单文件上传下载如 excel、csv等
###1.excel 批量读取
在此case中，出去需要导入 easy-excel外还需要提供解析excel的apache一些工具包

###2.excel 批量导出且无oom

###3.spring-boot升级本版刀2.5以上之后引起的变动
####3.1. 对于code中的历史 junit test case 中使用的 @RunWith 引起的error 
笔者在 stackOverFlow 上看到有同学就此问题给与了具体的回答。 https://stackoverflow.com/questions/55276555/when-to-use-runwith-and-when-extendwith .
````java
@ExtendWith(MockitoExtension.class)
class CommonTest {

    @Mock
    HashMap<String, Integer> hashMap;

    @Captor
    ArgumentCaptor<String> keyCaptor;

    @Captor
    ArgumentCaptor<Integer> valueCaptor;

    @Test
    public void saveTest()
    {
        hashMap.put("A", 10);
        hashMap.put("b",20);
        Mockito.verify(hashMap,new Times(2)).put(keyCaptor.capture(), valueCaptor.capture());
        assertEquals("b", keyCaptor.getValue());
        assertEquals(new Integer(20), valueCaptor.getValue());
    }
}
````


