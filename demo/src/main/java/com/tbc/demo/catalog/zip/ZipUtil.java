package com.tbc.demo.catalog.zip;

import com.tbc.demo.utils.MD5Generator;
import sun.security.provider.MD5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * ① 将长字符串用md5算法生成32位签名串，分为4段,，每段8个字符；<br />
 * <p>
 * ② 对这4段循环处理，取每段的8个字符, 将他看成16进制字符串与0x3fffffff(30位1)的位与操作，超过30位的忽略处理；<br />
 * <p>
 * ③ 将每段得到的这30位又分成6段，每5位的数字作为字母表的索引取得特定字符，依次进行获得6位字符串；<br />
 * <p>
 * ④ 这样一个md5字符串可以获得4个6位串，取24位字符的前12位。<br />
 */

public class ZipUtil {

    public static void main(String[] args) throws IOException {

        String content = "<p style='text-align: right;'><span style='text-decoration: underline; color: rgb(255, 0, 0);'"
                + "><em><strong>Dear All,&nbsp;&nbsp;</strong></em></span></p><p><br/></p><p>近期Jerry Shih会来到AGS。"
                + "</p><p><br/></p><p>作为AGS的一员，请大家配合以下事项，让我们共同展现出AGS的专业风貌！</p><p><b"
                + "r/></p><p>时间：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请按时上、下班，避免"
                + "迟到或早退。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;中午按时吃饭（12:00），"
                + "请不要提前。</p><p><br/></p><p>公共设施：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &n"
                + "bsp;中午请大家在就餐区吃午饭，请不要在会议室或座位上就餐。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp;"
                + " &nbsp; &nbsp;带饭的同仁请不要把饭盒放在吧台上，请放在冰箱。</p><p><br/></p><p>·&nbsp; &nbsp; &"
                + "nbsp; &nbsp; &nbsp;最后下班的同事请确认所有空调、灯等设备电源是否关闭。</p><p><br/></p><p st"
                + "yle='text-align: justify;'>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;VIP厕所请不要使用(哺乳期"
                + "员工除外)。</p><p><br/></p><p>仪表：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbs"
                + "p;着装需正式，颜色庄重、款式大方，请不要穿着休闲装。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp"
                + "; &nbsp; &nbsp;请避免大声喧哗。</p><p><br/></p><p>办公环境：</p><p><br/></p><p>·&nbsp; &nbsp"
                + "; &nbsp; &nbsp; &nbsp;请保证办公桌桌面的整洁。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp"
                + "; &nbsp;外套、围巾等服饰请挂在衣架或收进柜子里，请不要搭在椅背或放在桌面上。</p><p><br/></p><p>信息"
                + "安全：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;离开座位时请锁掉电脑。</p><p><br/>"
                + "</p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;涉及保密信息的文件请不要放在桌面上。</p><p><br/></"
                + "p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请不要把信息留在白板上。</p><p><br/></p><p>HR &amp; A"
                + "dmin</p><p><br/></p><p>AMAX Global Services</p><p><br/></p>"
                + "<p style='text-align: right;'><span style='text-decoration: underline; color: rgb(255, 0, 0);'"
                + "><em><strong>Dear All,&nbsp;&nbsp;</strong></em></span></p><p><br/></p><p>近期Jerry Shih会来到AGS。"
                + "</p><p><br/></p><p>作为AGS的一员，请大家配合以下事项，让我们共同展现出AGS的专业风貌！</p><p><b"
                + "r/></p><p>时间：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请按时上、下班，避免"
                + "迟到或早退。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;中午按时吃饭（12:00），"
                + "请不要提前。</p><p><br/></p><p>公共设施：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &n"
                + "bsp;中午请大家在就餐区吃午饭，请不要在会议室或座位上就餐。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp;"
                + " &nbsp; &nbsp;带饭的同仁请不要把饭盒放在吧台上，请放在冰箱。</p><p><br/></p><p>·&nbsp; &nbsp; &"
                + "nbsp; &nbsp; &nbsp;最后下班的同事请确认所有空调、灯等设备电源是否关闭。</p><p><br/></p><p st"
                + "yle='text-align: justify;'>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;VIP厕所请不要使用(哺乳期"
                + "员工除外)。</p><p><br/></p><p>仪表：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbs"
                + "p;着装需正式，颜色庄重、款式大方，请不要穿着休闲装。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp"
                + "; &nbsp; &nbsp;请避免大声喧哗。</p><p><br/></p><p>办公环境：</p><p><br/></p><p>·&nbsp; &nbsp"
                + "; &nbsp; &nbsp; &nbsp;请保证办公桌桌面的整洁。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp"
                + "; &nbsp;外套、围巾等服饰请挂在衣架或收进柜子里，请不要搭在椅背或放在桌面上。</p><p><br/></p><p>信息"
                + "安全：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;离开座位时请锁掉电脑。</p><p><br/>"
                + "</p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;涉及保密信息的文件请不要放在桌面上。</p><p><br/></"
                + "p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请不要把信息留在白板上。</p><p><br/></p><p>HR &amp; A"
                + "dmin</p><p><br/></p><p>AMAX Global Services</p><p><br/></p>"
                + "><em><strong>Dear All,&nbsp;&nbsp;</strong></em></span></p><p><br/></p><p>近期Jerry Shih会来到AGS。"
                + "</p><p><br/></p><p>作为AGS的一员，请大家配合以下事项，让我们共同展现出AGS的专业风貌！</p><p><b"
                + "r/></p><p>时间：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请按时上、下班，避免"
                + "迟到或早退。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;中午按时吃饭（12:00），"
                + "请不要提前。</p><p><br/></p><p>公共设施：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &n"
                + "bsp;中午请大家在就餐区吃午饭，请不要在会议室或座位上就餐。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp;"
                + " &nbsp; &nbsp;带饭的同仁请不要把饭盒放在吧台上，请放在冰箱。</p><p><br/></p><p>·&nbsp; &nbsp; &"
                + "nbsp; &nbsp; &nbsp;最后下班的同事请确认所有空调、灯等设备电源是否关闭。</p><p><br/></p><p st"
                + "yle='text-align: justify;'>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;VIP厕所请不要使用(哺乳期"
                + "员工除外)。</p><p><br/></p><p>仪表：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbs"
                + "p;着装需正式，颜色庄重、款式大方，请不要穿着休闲装。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp"
                + "; &nbsp; &nbsp;请避免大声喧哗。</p><p><br/></p><p>办公环境：</p><p><br/></p><p>·&nbsp; &nbsp"
                + "; &nbsp; &nbsp; &nbsp;请保证办公桌桌面的整洁。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp"
                + "; &nbsp;外套、围巾等服饰请挂在衣架或收进柜子里，请不要搭在椅背或放在桌面上。</p><p><br/></p><p>信息"
                + "安全：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;离开座位时请锁掉电脑。</p><p><br/>"
                + "</p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;涉及保密信息的文件请不要放在桌面上。</p><p><br/></"
                + "p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请不要把信息留在白板上。</p><p><br/></p><p>HR &amp; A"
                + "dmin</p><p><br/></p><p>AMAX Global Services</p><p><br/></p>"
                + "<p style='text-align: right;'><span style='text-decoration: underline; color: rgb(255, 0, 0);'"
                + "><em><strong>Dear All,&nbsp;&nbsp;</strong></em></span></p><p><br/></p><p>近期Jerry Shih会来到AGS。"
                + "</p><p><br/></p><p>作为AGS的一员，请大家配合以下事项，让我们共同展现出AGS的专业风貌！</p><p><b"
                + "r/></p><p>时间：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请按时上、下班，避免"
                + "迟到或早退。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;中午按时吃饭（12:00），"
                + "请不要提前。</p><p><br/></p><p>公共设施：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &n"
                + "bsp;中午请大家在就餐区吃午饭，请不要在会议室或座位上就餐。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp;"
                + " &nbsp; &nbsp;带饭的同仁请不要把饭盒放在吧台上，请放在冰箱。</p><p><br/></p><p>·&nbsp; &nbsp; &"
                + "nbsp; &nbsp; &nbsp;最后下班的同事请确认所有空调、灯等设备电源是否关闭。</p><p><br/></p><p st"
                + "yle='text-align: justify;'>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;VIP厕所请不要使用(哺乳期"
                + "员工除外)。</p><p><br/></p><p>仪表：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbs"
                + "p;着装需正式，颜色庄重、款式大方，请不要穿着休闲装。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp"
                + "; &nbsp; &nbsp;请避免大声喧哗。</p><p><br/></p><p>办公环境：</p><p><br/></p><p>·&nbsp; &nbsp"
                + "; &nbsp; &nbsp; &nbsp;请保证办公桌桌面的整洁。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp"
                + "; &nbsp;外套、围巾等服饰请挂在衣架或收进柜子里，请不要搭在椅背或放在桌面上。</p><p><br/></p><p>信息"
                + "安全：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;离开座位时请锁掉电脑。</p><p><br/>"
                + "</p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;涉及保密信息的文件请不要放在桌面上。</p><p><br/></"
                + "p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请不要把信息留在白板上。</p><p><br/></p><p>HR &amp; A"
                + "dmin</p><p><br/></p><p>AMAX Global Services</p><p><br/></p>" + "><em><strong>Dear All,&nbsp;&nbsp;</strong></em></span></p><p><br/></p><p>近期Jerry Shih会来到AGS。"
                + "</p><p><br/></p><p>作为AGS的一员，请大家配合以下事项，让我们共同展现出AGS的专业风貌！</p><p><b"
                + "r/></p><p>时间：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请按时上、下班，避免"
                + "迟到或早退。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;中午按时吃饭（12:00），"
                + "请不要提前。</p><p><br/></p><p>公共设施：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &n"
                + "bsp;中午请大家在就餐区吃午饭，请不要在会议室或座位上就餐。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp;"
                + " &nbsp; &nbsp;带饭的同仁请不要把饭盒放在吧台上，请放在冰箱。</p><p><br/></p><p>·&nbsp; &nbsp; &"
                + "nbsp; &nbsp; &nbsp;最后下班的同事请确认所有空调、灯等设备电源是否关闭。</p><p><br/></p><p st"
                + "yle='text-align: justify;'>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;VIP厕所请不要使用(哺乳期"
                + "员工除外)。</p><p><br/></p><p>仪表：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbs"
                + "p;着装需正式，颜色庄重、款式大方，请不要穿着休闲装。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp"
                + "; &nbsp; &nbsp;请避免大声喧哗。</p><p><br/></p><p>办公环境：</p><p><br/></p><p>·&nbsp; &nbsp"
                + "; &nbsp; &nbsp; &nbsp;请保证办公桌桌面的整洁。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp"
                + "; &nbsp;外套、围巾等服饰请挂在衣架或收进柜子里，请不要搭在椅背或放在桌面上。</p><p><br/></p><p>信息"
                + "安全：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;离开座位时请锁掉电脑。</p><p><br/>"
                + "</p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;涉及保密信息的文件请不要放在桌面上。</p><p><br/></"
                + "p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请不要把信息留在白板上。</p><p><br/></p><p>HR &amp; A"
                + "dmin</p><p><br/></p><p>AMAX Global Services</p><p><br/></p>"
                + "<p style='text-align: right;'><span style='text-decoration: underline; color: rgb(255, 0, 0);'"
                + "><em><strong>Dear All,&nbsp;&nbsp;</strong></em></span></p><p><br/></p><p>近期Jerry Shih会来到AGS。"
                + "</p><p><br/></p><p>作为AGS的一员，请大家配合以下事项，让我们共同展现出AGS的专业风貌！</p><p><b"
                + "r/></p><p>时间：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请按时上、下班，避免"
                + "迟到或早退。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;中午按时吃饭（12:00），"
                + "请不要提前。</p><p><br/></p><p>公共设施：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &n"
                + "bsp;中午请大家在就餐区吃午饭，请不要在会议室或座位上就餐。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp;"
                + " &nbsp; &nbsp;带饭的同仁请不要把饭盒放在吧台上，请放在冰箱。</p><p><br/></p><p>·&nbsp; &nbsp; &"
                + "nbsp; &nbsp; &nbsp;最后下班的同事请确认所有空调、灯等设备电源是否关闭。</p><p><br/></p><p st"
                + "yle='text-align: justify;'>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;VIP厕所请不要使用(哺乳期"
                + "员工除外)。</p><p><br/></p><p>仪表：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbs"
                + "p;着装需正式，颜色庄重、款式大方，请不要穿着休闲装。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp"
                + "; &nbsp; &nbsp;请避免大声喧哗。</p><p><br/></p><p>办公环境：</p><p><br/></p><p>·&nbsp; &nbsp"
                + "; &nbsp; &nbsp; &nbsp;请保证办公桌桌面的整洁。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp"
                + "; &nbsp;外套、围巾等服饰请挂在衣架或收进柜子里，请不要搭在椅背或放在桌面上。</p><p><br/></p><p>信息"
                + "安全：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;离开座位时请锁掉电脑。</p><p><br/>"
                + "</p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;涉及保密信息的文件请不要放在桌面上。</p><p><br/></"
                + "p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请不要把信息留在白板上。</p><p><br/></p><p>HR &amp; A"
                + "dmin</p><p><br/></p><p>AMAX Global Services</p><p><br/></p>"
                + "><em><strong>Dear All,&nbsp;&nbsp;</strong></em></span></p><p><br/></p><p>近期Jerry Shih会来到AGS。"
                + "</p><p><br/></p><p>作为AGS的一员，请大家配合以下事项，让我们共同展现出AGS的专业风貌！</p><p><b"
                + "r/></p><p>时间：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请按时上、下班，避免"
                + "迟到或早退。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;中午按时吃饭（12:00），"
                + "请不要提前。</p><p><br/></p><p>公共设施：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &n"
                + "bsp;中午请大家在就餐区吃午饭，请不要在会议室或座位上就餐。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp;"
                + " &nbsp; &nbsp;带饭的同仁请不要把饭盒放在吧台上，请放在冰箱。</p><p><br/></p><p>·&nbsp; &nbsp; &"
                + "nbsp; &nbsp; &nbsp;最后下班的同事请确认所有空调、灯等设备电源是否关闭。</p><p><br/></p><p st"
                + "yle='text-align: justify;'>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;VIP厕所请不要使用(哺乳期"
                + "员工除外)。</p><p><br/></p><p>仪表：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbs"
                + "p;着装需正式，颜色庄重、款式大方，请不要穿着休闲装。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp"
                + "; &nbsp; &nbsp;请避免大声喧哗。</p><p><br/></p><p>办公环境：</p><p><br/></p><p>·&nbsp; &nbsp"
                + "; &nbsp; &nbsp; &nbsp;请保证办公桌桌面的整洁。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp"
                + "; &nbsp;外套、围巾等服饰请挂在衣架或收进柜子里，请不要搭在椅背或放在桌面上。</p><p><br/></p><p>信息"
                + "安全：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;离开座位时请锁掉电脑。</p><p><br/>"
                + "</p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;涉及保密信息的文件请不要放在桌面上。</p><p><br/></"
                + "p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请不要把信息留在白板上。</p><p><br/></p><p>HR &amp; A"
                + "dmin</p><p><br/></p><p>AMAX Global Services</p><p><br/></p>"
                + "<p style='text-align: right;'><span style='text-decoration: underline; color: rgb(255, 0, 0);'"
                + "><em><strong>Dear All,&nbsp;&nbsp;</strong></em></span></p><p><br/></p><p>近期Jerry Shih会来到AGS。"
                + "</p><p><br/></p><p>作为AGS的一员，请大家配合以下事项，让我们共同展现出AGS的专业风貌！</p><p><b"
                + "r/></p><p>时间：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请按时上、下班，避免"
                + "迟到或早退。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;中午按时吃饭（12:00），"
                + "请不要提前。</p><p><br/></p><p>公共设施：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &n"
                + "bsp;中午请大家在就餐区吃午饭，请不要在会议室或座位上就餐。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp;"
                + " &nbsp; &nbsp;带饭的同仁请不要把饭盒放在吧台上，请放在冰箱。</p><p><br/></p><p>·&nbsp; &nbsp; &"
                + "nbsp; &nbsp; &nbsp;最后下班的同事请确认所有空调、灯等设备电源是否关闭。</p><p><br/></p><p st"
                + "yle='text-align: justify;'>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;VIP厕所请不要使用(哺乳期"
                + "员工除外)。</p><p><br/></p><p>仪表：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbs"
                + "p;着装需正式，颜色庄重、款式大方，请不要穿着休闲装。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp"
                + "; &nbsp; &nbsp;请避免大声喧哗。</p><p><br/></p><p>办公环境：</p><p><br/></p><p>·&nbsp; &nbsp"
                + "; &nbsp; &nbsp; &nbsp;请保证办公桌桌面的整洁。</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp"
                + "; &nbsp;外套、围巾等服饰请挂在衣架或收进柜子里，请不要搭在椅背或放在桌面上。</p><p><br/></p><p>信息"
                + "安全：</p><p><br/></p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;离开座位时请锁掉电脑。</p><p><br/>"
                + "</p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;涉及保密信息的文件请不要放在桌面上。</p><p><br/></"
                + "p><p>·&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;请不要把信息留在白板上。</p><p><br/></p><p>HR &amp; A"
                + "dmin</p><p><br/></p><p>AMAX Global Services</p><p><br/></p>";
        System.out.println(content);
        System.out.println("字符串长度为：" + content.length());


        String ysStr = compress(content);
        System.out.println("\n压缩后的字符串为----->" + ysStr);
        float len1 = ysStr.length();
        System.out.println("压缩后的字符串长度为----->" + len1);

        String jyStr = unCompress(ysStr);
        System.out.println("\n解压缩后的字符串为--->" + jyStr);
        System.out.println("解压缩后的字符串长度为--->" + jyStr.length());

        //判断
        if (jyStr.equals(content)) {
            System.out.println("解压后的字符串是一样的。。。。");
        } else {
            System.out.println("解压后的字符串不是一样的。。。。");
        }

    }


    /**
     * 字符串的压缩
     */
    public static String compress(String str) throws IOException {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 使用默认缓冲区大小创建新的输出流
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        // 将 b.length 个字节写入此输出流
        gzip.write(str.getBytes());
        gzip.close();
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString("ISO-8859-1");
    }

    /**
     * 字符串的解压
     */
    public static String unCompress(String str) throws IOException {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
        ByteArrayInputStream in = new ByteArrayInputStream(str
                .getBytes("ISO-8859-1"));
        // 使用默认缓冲区大小创建新的输入流
        GZIPInputStream gzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n = 0;
        while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组
            // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
            out.write(buffer, 0, n);
        }
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString("GBK");


    }
}
