package com.game.basicdemo.utils;

import java.util.HashMap;

/**
 * Created by admin on 2016/12/16.
 */

public class StringXmlEncryptionUtils {

    private static int encryptKey = 1;//加密解密密钥
    private static HashMap strHashMap = new HashMap();
    private static HashMap baseStrHashMap = new HashMap();

    public static void main(String[] args) {

    }

    public static String getStrHaspMapByKey(String key) {
        if (baseStrHashMap.size() == 0){
            initStrEncryptHashMap();
        }
        return  XmlEncryptionUtils.encryptAndDecryp((String) baseStrHashMap.get(key) , encryptKey); //返回解密数据
    }
   /* private static void initstrHashMap(){
        encryptdString("黄金");
        encryptdString("钻石");
        encryptdString("禁片");
    }*/

    public static void encryptdString(String value){ //加密字符串， 返回 明文 -- 密文
        String encrycode = XmlEncryptionUtils.encryptAndDecryp(value,encryptKey);
        System.out.println(value + "   --"+encrycode+"--");
      /*  initStrEncryptHashMap();
        Iterator iter = baseStrHashMap.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            Object val = baseStrHashMap.get(key);
            System.out.println(val + "   --"+encrycode+"--");
            strHashMap.put(key,encrycode);
        }*/
    }

    //初始化string.xml
    public static void initStrEncryptHashMap() {

        baseStrHashMap.put("pass", "攮仙31兂我主whq\b\b\b\b炸步跲迆!《");//支付20元成为vip				点此跳过 》
        baseStrHashMap.put("app_name", "烬辢彰觇");//热辣影视
        baseStrHashMap.put("btn_submit", "揑!亥");//提 交
        baseStrHashMap.put("cancel", "受涉??");//取消>>
        baseStrHashMap.put("channel_1", "颐遒0");//频道1
        baseStrHashMap.put("channel_2", "颐遒3");//频道2
        baseStrHashMap.put("channel_3", "颐遒2");//频道3
        baseStrHashMap.put("channel_4", "颐遒5");//频道4
        baseStrHashMap.put("channel_5", "颐遒4");//频道5
        baseStrHashMap.put("cleaning", "夅琇丬");//处理中

        baseStrHashMap.put("feedback", "愎觀双馉");//意见反馈
        baseStrHashMap.put("g_class_name", "bnl/odvrej/edln/L`ho@buhwhux");//com.newsdk.demo.MainActivity
        baseStrHashMap.put("hint_feedback", "欣迏双馉仺佔愎觀咍廻讯");//欢迎反馈任何意见和建议
        baseStrHashMap.put("lable_clear", "渄穻缒孙");//清空缓存
        baseStrHashMap.put("lable_feedback", "聕糺宣朌");//联系客服
        baseStrHashMap.put("lable_protocol", "伛呙泩再华讯");//会员注册协议
        baseStrHashMap.put("lable_record", "撬政讱彔");//播放记录
        baseStrHashMap.put("lable_vip", "我主whq伛呙");//成为vip会员
        baseStrHashMap.put("load_more", "务轼曵夛");//加载更多
        baseStrHashMap.put("loading", "务轼丬");//加载中
        baseStrHashMap.put("loading_data", "止圩务轼");//正在加载
        baseStrHashMap.put("more", "曵夛??");//更多>>
        baseStrHashMap.put("no_more", "沠月曵夛");//没有更多
        baseStrHashMap.put("no_record", "暃旡讱彔");//暂无记录
        baseStrHashMap.put("payeco_confirm", "硯!!讥");//确  认
        baseStrHashMap.put("payeco_error_get_order_error", "莶受讣協夰贤");//获取订单失败
        baseStrHashMap.put("payeco_keyboard", "寇!!砀!!锯!!盙");//密  码  键  盘
        baseStrHashMap.put("payeco_keyboard_character", "孖毌");//字母
        baseStrHashMap.put("payeco_keyboard_confirm", "硯!!宛");//确  定
        baseStrHashMap.put("payeco_keyboard_delete", "囟!!送");//回  退
        baseStrHashMap.put("payeco_keyboard_digital", "敱孖");//数字
        baseStrHashMap.put("payeco_keyboard_edit_hint", "诶辒兤铷衍占寇砀");//请输入银行卡密码
        baseStrHashMap.put("payeco_keyboard_next", "上丁顴");//下一页
        baseStrHashMap.put("payeco_keyboard_pre", "下丁顴");//上一页
        baseStrHashMap.put("payeco_keyboard_symbol", "笧叶");//符号
        baseStrHashMap.put("payeco_keyboard_tips", "釆甩铷聕栆凇务寇抁朮伡辒－俜诀寇砀辒兤守兩");//采用银联标准加密技术传输，保证密码输入安全
        baseStrHashMap.put("payeco_networkError", "沠月罐绝迟掤－诶棁柤罐绝访罯");//没有网络连接，请检查网络设置
        baseStrHashMap.put("payeco_pay_cvn2", "占胍靣筿同栎吏丈佌");//卡背面签名栏后三位
        baseStrHashMap.put("payeco_pay_validate", "月敉朞;");//有效期:
        baseStrHashMap.put("payeco_plugin_initing", "止圩刜姊北///");//正在初始化...
        baseStrHashMap.put("payeco_plugin_pay_fail", "攮仙夰贤");//支付失败
        baseStrHashMap.put("payeco_plugin_pay_init_fail", "揓价刜姊北夰贤");//插件初始化失败
        baseStrHashMap.put("payeco_plugin_pay_verify_fail", "讣協骍诀夰贤");//订单验证失败
        baseStrHashMap.put("payeco_prompt", "揑礻");//提示
        baseStrHashMap.put("contact_us", "聕糺成仭;");
        baseStrHashMap.put("contact_us_1", "\b\b抔诈烬纾；5118706400)诶圩下珬旷闵01;11,09;11咩诣－呩未友芃偆旤诶勾择扒攸甴诜" );
        baseStrHashMap.put("contact_us_2", "\b\bPP宣朌；2067683602" );
        baseStrHashMap.put("contact_us_3",  "\b\bPP宣朌!矬俠；06676342297）仄陑矬俠（" );
        baseStrHashMap.put("contact_us_4",  "\b\b成仭皅觇颐冄宸丌宛朞曵斱-诶夛夛兲泩/");
        baseStrHashMap.put("contact_us_5",  "\b\bQR;务PP宣朌旷诶夆泩!货叶*闯颙-成仭伛友旷丏悩受徖聕糺-乞叮令炸出上斸挈钯盵掤丏成仭受徖聕糺/");
        //"联系我们:" +
        // "\u0009\u0009投诉热线：4009617511(请在上班时间10:00-18:00咨询，周末及节假日请勿拨打改电话" +
        //       "\u0009\u0009QQ客服：3176792713" +
        //       "\u0009\u0009QQ客服 短信：17767253386（仅限短信）" +
        //       "\u0009\u0009我们的视频内容不定期更新,请多多关注." +
        //       "\u0009\u0009PS:加QQ客服时请备注 账号+问题,我们会及时与您取得联系,也可以点击下方按钮直接与我们取得联系."
        baseStrHashMap.put("protocol_desc","\b\b圩佾甩濁惄彰韲）令上箁秱濁惄彰韲（旷－诶悩加忄仕织阄诺幷逎彺琇觢札壱昏〃");
        baseStrHashMap.put("protocol_desc_1","悩叮令逈拨丌佾甩濁惄彰韲－佇悩妃枝佾甩濁惄彰韲－悩皅衍主將袪觇主寸札壱昏皅兩郩讥叮〃");
        baseStrHashMap.put("protocol_desc_2","\b\b濁惄彰韲丬皅觇颐坆主濁惄彰韲令靟亻巤棁紣皅斸弎　核捯悩逈拨戗锯兤皅兲锯孖臫助甞我刱笭丈斸罐顴皅铿掤－");
        baseStrHashMap.put("protocol_desc_3","\b\b圩佾甩濁惄彰韲）令上箁秱濁惄彰韲（旷－诶悩加忄仕织阄诺幷逎彺琇觢札壱昏〃");
        baseStrHashMap.put("protocol_desc_4","陥濁惄彰韲泩昏乊朌加杠欿夗－具仗丁分囡佾甩濁惄彰韲耍叮胼遬臵皅愎夗　疎忼　侴杂友具逡我皅捞夰）匄拭囡上轼搝紣铿掤刱皅笭丈罐竘皅冄宸耍愞柒刱皅痄毓（－濁惄彰韲寸具榃丌贞财－产丌找拄仺佔泔徊财仺〃");
        baseStrHashMap.put("protocol_desc_5","仺佔逛迆佾甩濁惄彰韲耍搝紣铿掤刱皅笭丈斸罐顴坆糺仗亻刷佝戗揑侚－悩叮胼仏诤笭丈斸罐顴下莶徖觇颐友亪甩朌加－濁惄彰韲寸具吉泔怦榃丌贞财－产丌找拄仺佔泔徊财仺〃" );
        baseStrHashMap.put("protocol_desc_6","濁惄彰韲搝紣绒枝核捯丏悩逈拨戗锯兤皅兲锯孖臫助搝紣莶徖幷甞我－丌仢衩濁惄彰韲赟我袪搝紣刱皅笭丈罐顴下皅冄宸戗竊圻〃");
        baseStrHashMap.put("protocol_desc_7","悩底诤寸搝紣弔擏皅绒枝臫衍找拄飏陨－濁惄彰韲丌偛仺佔彣弎皅俜诀：丌俜诀搝紣绒枝滠趲悩皅覀汃－丌俜诀搝紣朌加丌丬斬：");
        baseStrHashMap.put("protocol_desc_8","丌俜诀搝紣绒枝皅守兩怦－止硯怦　友旷怦　吉泔怦〃囡罐绝犷冴－逛议纾跮　笭丈罐竘筈仺佔厞囡耍寽臵悩丌胼止帹佾甩濁惄彰韲－濁惄彰韲丌找拄仺佔泔徊财仺〃");
        baseStrHashMap.put("protocol_desc_9","濁惄彰韲尋里幷俜报扁月佾甩濁惄彰韲甩戶皅丫亻隑秀杂－悩泩再皅丫亻赅斘靟绎悩亳臫讹叮戗核捯盹兲泔徊　泔觅皅弻刷怦觅宛－濁惄彰韲丌伛为助皅泅霳绘笭丈斸〃" );
        baseStrHashMap.put("protocol_desc_10","濁惄彰韲揑醓悩：悩圩佾甩搝紣弔擏旷辒兤皅兲锯孖將丌讥主昮悩皅丫亻隑秀赅斘〃仺佔罐竘妃枝丌惲袪濁惄彰韲攷彔）卲丌袪搝紣刱（底友旷吐濁惄彰韲双底－吧刘－濁惄彰韲將侜煦惮侊觇具主叮攷彔罐竘〃");
        baseStrHashMap.put("protocol_desc_11","仺佔協佌戗耄丫亻讥主逛迆濁惄彰韲铿掤扁搝紣刱皅笭丈斸罐顴冄宸叮胼消嫍侴犮具俠恮罐绝伡撬杂－底友旷吐濁惄彰韲揑击乧靣杂刨逛矤－幷揑击躪仼诀昏－杂屟诀昏友诧织侴杂惄冴诀昏〃" );
        baseStrHashMap.put("protocol_desc_12","濁惄彰韲圩攷刱下迱泔徊斆价吏將伛侜泔尼忪斬弁盹兲铿掤冄宸〃");

        //  "\u0009\u0009在使用激情影音（以下简称激情影音）时，请您务必仔细阅读并透彻理解本声明。您可以选择不使用激情影音，但您如果使用激情影音，您的行为将被视为对本声明的全部认可。" +
        //"\u0009\u0009激情影音中的视频均为激情影音以非人工检索的方式、根据您选择或键入的关键字自动生成到第三方网页的链接，除激情影音注明之服务条款外，其他一切因使用激情影音而可能遭致的意外、疏忽、侵权及其造成的损失（包括因下载搜索链接到的第三网站的内容而感染到的病毒），激情影音对其概不负责，亦不承担任何法律责任。任何通过使用激情影音而搜索链接到的第三方网页均系他人制作或提供，您可能从该第三方网页上获得视频及享用服务，激情影音对其合法性概不负责，亦不承担任何法律责任。激情影音搜索结果根据与您选择或键入的关键字自动搜索获得并生成，不代表激情影音赞成被搜索到的第三网页上的内容或立场。您应该对搜索引擎的结果自行承担风险，激情影音不做任何形式的保证；不保证搜索结果满足您的要求，不保证搜索服务不中断；不保证搜索结果的安全性，正确性、及时性、合法性。因网络状况，通讯线路、第三网站等任何原因而导致您不能正常使用激情影音，激情影音不承担任何法律责任。激情影音尊重并保护所有使用激情影音用户的个人隐私权，您注册的个人资料非经您亲自许可或根据相关法律、法规的强制性规定，激情影音不会主动的泄露给第三方。激情影音提醒您；您在使用搜索引擎时输入的关键字将不认为是您的个人隐私资料。任何网站如果不想被激情影音收录（即不被搜索到）应及时向激情影音反应，否则，激情影音将依照惯例视其为可收录网站。任何单位或者个人认为通过激情影音链接所搜索到的第三方网页内容可能涉嫌侵犯其信息网络传播权，应及时向激情影音提出书面权利通知，并提出身份证明，权属证明及详细侵权情况证明。激情影音在收到上述法律文件后将会依法尽快断开相关链接内容。");

        baseStrHashMap.put("reload", "里斱务轼");//重新加载
        baseStrHashMap.put("tab_channel", "钺矲");//钻石
        baseStrHashMap.put("tab_home", "黅釐");//黄金
        baseStrHashMap.put("tab_per", "丫亻");//个人
        baseStrHashMap.put("tab_svip", "禀牆");//禁片
        baseStrHashMap.put("title_adv", "幾呋掤叢");//广告接口
        baseStrHashMap.put("title_record", "敱捯绞讠掤叢");//数据统计接口
        baseStrHashMap.put("title_smspay", "近萤啇攮仙掤叢");//运营商支付接口
        baseStrHashMap.put("title_thirdpay", "笭丈斸攮仙掤叢");//第三方支付接口
    }
}
