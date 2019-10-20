package cn.ihep.jdy.release.controller.accpter;

import cn.ihep.jdy.release.Util.JPush.JPushConfig;
import cn.ihep.jdy.release.dao.pojoRepository.*;
import cn.ihep.jdy.release.service.ManagerService.ManagerService;
import cn.ihep.jdy.release.service.cache.CacheService;
import cn.ihep.jdy.release.service.common.CommonService;
import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * 此处可以是静态引用，也可以不用静态引用，这样就需要在使用方法的时候加上对应的类
 */



/**
 * UserController Tester.
 *
 * @author <Authors name>
 * @since <pre>Oct 13, 2019</pre>
 * @version 1.0
 */
//测试模板 修改url,name,json语句就行了
//           String url="/manager/changegroups";
//           String name="manager";
//          String user= JSONbuildUtil.toJsonString();
//          RequestBuilder request = post(url)
//          .contentType(MediaType.APPLICATION_JSON_UTF8)
//          .param(name,user);
//          System.out.println("请求：" );
//          System.out.println(request.toString());
//
//          MvcResult mvcResult = mockMvc.perform(request).andDo(print()).andReturn();
@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class springMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AiCustomerRepository aiCustomerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerFollowRepository customerFollowRepository;

    @Autowired
    private CustomerVisitRepository customerVisitRepository;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AiUserRepository aiUserRepository;

    @Autowired
    private ManagerService groupService;
    @Autowired
    private JPushConfig jPushConfig;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private RobotRecordRepository robotRecordRepository;

    @Autowired
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void SpringJpaTest() throws Exception {
//TODO: Test goes here...

    }
    @Test
    public void SpringMvcTest() throws Exception {
//TODO: Test goes here...
        String url= (String) toJsonString().get("url@#");
        String JSONStr=(String) toJsonString().get("JSONStr@#");
        String paramName=(String) toJsonString().get("paramName@#");
        mvcTest(url,JSONStr,paramName);

    }

    public void mvcTest(String url,String user, String name)throws Exception{
        RequestBuilder request = post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param(name,user);
        System.out.println("请求：" );
        System.out.println(request.toString());
        MvcResult mvcResult = mockMvc.perform(request).andDo(print()).andReturn();
    }
    @Test
    public void mvcTest2()throws Exception{
        RequestBuilder request = post("/company/uploadLogo.action")
                .contentType(MediaType.MULTIPART_FORM_DATA)
               // .param("name","user")
              //  .param("mobile","12131412")
              // .content("accepterPhoto3296.jpg")
               .param("aiUserId","7")
                ;
        System.out.println("请求：" );
        System.out.println(request.toString());
        MvcResult mvcResult = mockMvc.perform(request).andDo(print()).andReturn();
    }
    public static Map toJsonString(){

        List<Long> aiusers=new ArrayList<Long>();
        aiusers.add(7L);
        aiusers.add(8L);
        aiusers.add(9L);
        List<Long> timesList=new ArrayList<Long>();
        timesList.add(12L);
        timesList.add(32L);
        timesList.add(45L);
        Map<String,Object> map1 = new HashMap<String,Object>();
        Map<String,Object> map2 = new HashMap<String,Object>();

        String JSONStr;
        //修改url
        map1.put("url@#","/customer/first.action");
        //修改映射名字
        map1.put("paramName@#","accepter");
        //修改json字符串内容


        // map2.put("aiuserIds","[7,8,9]");
        // map2.put("GroupId",3L);
       //   map2.put("userId",7);
        // map2.put("customerId",18L);
      //  map2.put("userId",137L);
      //  map2.put("robotId",3296L);
      //  map2.put("customerLogo","kehu123121245678");
        // map2.put("indexlist",timesList);
        // map2.put("times",4L);
        //map2.put("userId",7L);
         map2.put("aiUserId",7);
        String JSONStrContent = JSON.toJSONString(map2);



        System.out.println("JSONStr@#="+JSONStrContent);

        map1.put("JSONStr@#",JSONStrContent);

        return  map1;
    }
}

