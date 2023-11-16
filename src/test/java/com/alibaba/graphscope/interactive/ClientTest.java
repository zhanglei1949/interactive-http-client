package com.alibaba.graphscope.interactive;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ClientTest {
    private static Client client;

    @BeforeClass
    public static void beforeClass() {
        client = new Client("localhost:10000");
    }

    @Test
    public void testQuery0() {
        client.SubmitCtrlQuery(520201007000000062L, 5, 0.5, 10);
    }
    @Test
    public void testQuery1() {
        client.SubmitCtrlQuery(520201007000000255, 5, 0.5, 10);
    }

    @Test
    public void testQuery2() {
        List<Long> ids = Arrays.asList(
                1230118000014018178L, 1221117000016036801L, 1180731001224652820L, 1221117000017575180L, 1180716005004678513L,
                1180716045428054751L, 1210329000214919854L, 1230106000007476317L, 1190824000001575203L, 1220810000014048518L,
                1180716080232199220L, 1191163000023256296L, 1190916002573489934L, 1180716032725079854L, 1190869000010154421L,
                1180716030554602086L, 1210707000098765997L, 1220105000040061970L, 1200723000115695877L, 1191223000031609529L,
                1200708000032941103L, 1190916003570110000L, 1210729000147939738L, 1211126000039138421L, 1191058000037118267L,
                1180716026701138065L, 1181101000511866465L, 1190322000983837542L, 1190268990003833228L, 1180716015927517514L,
                1180716070451975086L, 1220423000011549876L, 1220319000045575170L, 1180716029307130942L, 1180716074916396890L,
                1200520000010968764L, 1230222000027808316L, 1221011000008792017L, 1230221000007125962L, 1190711000008478620L,
                1211103000033107181L, 1190518000009901243L, 1220228000044723510L, 1211210000040974005L, 1230215000014618753L,
                1200318000010966620L, 1180716004989037867L, 1220121000017645554L, 1180816000873075061L, 1200710000021006660L,
                1200331000010620070L, 1191221000508371711L, 1201228000074954391L, 1211123000048527643L, 1191169000003818042L,
                1200930000009543817L, 1191215000014799738L, 1180716006776506551L, 1180716028571788282L, 1190962000081673052L,
                1220423000011541589L, 1180716007853121823L, 1220209000025140799L, 1220524000138685100L, 1191024000485203478L,
                1210817000044154610L, 1180716005385333241L, 1180716004128151594L, 1180716024871421771L, 1191106000072589560L,
                1180716003867561598L, 1211216000043125461L, 1220126000047107681L, 1201218000120004193L, 1180716007948463257L,
                1191120035283988531L, 1200710000021177695L, 1200760000050912083L, 1200319000023241945L, 1180716074603337657L,
                1210510000055870226L, 1191221001833666968L, 1210706000070981647L, 1190828000007608148L, 1200461000085137347L,
                1201112000153480525L, 1200420000061764909L, 1180716073235842777L, 1180716012385532893L, 1190614000014187008L,
                1180716008541838780L, 1180716029225537025L, 1180716005412797334L, 1200710000021063979L, 1180716011491082220L,
                1210508000070663344L, 1230607000007694165L, 1210421000356850091L, 1200521000040826392L, 1180716015432135576L,
                1220125000056774963L, 1180716024861312251L, 1180716010241074511L, 1230207000026527999L, 1200715000404934483L,
                1190916002442722656L, 1211125000053551243L, 1180716025289409418L, 1180716003866843347L, 1180716011594825000L,
                1180716007828080224L, 1180716029318185993L, 1180716031340297036L, 1200904000004168386L, 1180716003856859506L,
                1201103000118732986L, 1220126000047370349L, 1190508000132129588L, 1180716012474465964L, 1180716027440646635L,
                1180716031344521152L, 1191163000024125315L, 1190745000058427876L
        );
        client.SubmitGroupQuery(9, ids, 200);
    }
}
