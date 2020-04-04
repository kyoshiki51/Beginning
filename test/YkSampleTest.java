package test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;

// JUnit�T���v��
class Method {
	
	WordsShop ws = new WordsShop();
	
	// �f�t�H���g�R���X�g���N�^
	public Method() {}

	/**
	 * �����œn���ꂽ��������A�w�肳�ꂽ�񐔂����������ĕԋp���郁�\�b�h�B
	 * �������ʂ��o�͂���B�_�߂Ă�������B
	 * 0�ȉ���n���Ă����y�ɂ́Anull�Ɣl�|�̌��t��������B
	 * @return ��������
	*/
	public String twice(String a, int num) {
		if (num > 0 ) {
			StringBuilder aNum = new StringBuilder(a);
			for(int i = 1; i < num; i++) {
				aNum.append(a);
			}
			System.out.println("�������ʁF" + aNum);
			System.out.println(ws.happyWord());
			return aNum.toString();
		} else {
			System.out.println(ws.unHappyWord());
			return null;
		}
	}
}

/**
 * ���t��ԋp����N���X
 * @author ������]
 *
 */
class WordsShop {
	// defaultConstractor
	public WordsShop() {}
	
	public String unhappy = "�΁[��";
	public String happy = "��������";
	
	/**
	 * �N���K���ɂ��Ȃ����\�b�h
	 * @return �l�|�̌��t
	 */
	public String unHappyWord() {
		return unhappy;
	}
	
	/**
	 * �݂�ȍK���ȃ��\�b�h
	 * `return �_�ߌ��t
	 */
	public String happyWord() {
		return happy;
	}
}

@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class YkSampleTest {
	// @Rule��TestName���`����ƃe�X�g���\�b�h�����Q�Ƃł���B
	@Rule
	public TestName testName = new TestName();

	// ErrorCollecctor���g�p���邽�߂ɒ�`����B
	@Rule
	public ErrorCollector collector = new ErrorCollector();

	// @Tested�ŃC���X�^���X�̐������ł���B�i���񐶐�����K�v���Ȃ��B�j�ň����Ȃ��̃R���X�g���N�^���Ă΂��B
	@Tested
	private Method method;
	
//	// @Mocked�Ń��b�N���ꂽ�C���X�^���X�𐶐��B�i���b�N+�Ē�`�Ȃ��̂��߁A�����������s��Ȃ��Ȃ�B�j
//	@Mocked
//	WordsShop wordsShop_;
	
	@Before
	public void before() {
		System.out.println("�y" + testName.getMethodName() + "�z:Start!");
	}

	@After
	public void after() {
		System.out.println("�y" + testName.getMethodName() + "�z:End!");
		System.out.println("");
	}
	
	@Test
	public void test001_���ʂɃe�X�g_1��Ăяo��() {
		String result = method.twice("3��J��Ԃ���", 3);
		collector.checkThat(result, is("3��J��Ԃ���3��J��Ԃ���3��J��Ԃ���"));
	}
	
	
	@Test
	public void test002_���ʂɃe�X�g_3��Ăяo��() {
		// �e�X�g�p�̃}�b�v�𐶐��B
		LinkedHashMap<String, Integer> testMap = new LinkedHashMap<>();
		testMap.put("�e�X�g1����", 1);
		testMap.put("�e�X�g2����", 2);
		testMap.put("�e�X�g3����", 3);
		
		StringBuilder sb = new StringBuilder();
		// �e�X�g�}�b�v�̕������e�X�g�����s����B
		for (String key : testMap.keySet()) {
			String result = method.twice(key, testMap.get(key));
			if (result != null) {
				sb.append(result);
			}
		}
		collector.checkThat("���ʂ̊m�F", sb.toString(), is("�e�X�g1����e�X�g2����e�X�g2����e�X�g3����e�X�g3����e�X�g3����"));
	}
	
	@Test
	public void test003_���ʂɃe�X�g_0�ȉ���1��Ăяo��() {
		String result = method.twice("0��J��Ԃ���", 0);
		collector.checkThat(result, is(nullValue()));
	}
	
	@Test
	public void test004_���ʂɃe�X�g_0�ȉ���3��Ăяo��() {
		// �e�X�g�p�̃}�b�v�𐶐��B
		LinkedHashMap<String, Integer> testMap = new LinkedHashMap<>();
		testMap.put("�e�X�g1����", 0);
		testMap.put("�e�X�g2����", 1);
		testMap.put("�e�X�g3����", -1);

		StringBuilder sb = new StringBuilder();
		// �e�X�g�}�b�v�̕������e�X�g�����s����B
		for (String key : testMap.keySet()) {
			String result = method.twice(key, testMap.get(key));
			if (result != null) {
				sb.append(result);
			}
		}
		collector.checkThat("�������ʂ̊m�F", sb.toString(), is("�e�X�g2����"));
	}
	
	@Test
	public void test005_Expectations(@Mocked Method method) {
		new Expectations(){{
			method.twice(anyString, anyInt); 
			minTimes = 0;
			maxTimes = 3;
			returns("1","2","3");
		}};
		
		// �e�X�g�p�̃}�b�v�𐶐��B
		LinkedHashMap<String, Integer> testMap = new LinkedHashMap<>();
		testMap.put("�e�X�g1����", 1);
		testMap.put("�e�X�g2����", 2);
		testMap.put("�e�X�g3����", 0);

		StringBuilder sb = new StringBuilder();
		// �e�X�g�}�b�v�̕������e�X�g�����s����B
		for (String key : testMap.keySet()) {
			String result = method.twice(key, testMap.get(key));
			if (result != null) {
				sb.append(result);
			}
		}
		collector.checkThat("�������ʂ̊m�F", sb.toString(), is("123"));
	}

	@Test
	public void test006_Verifications(@Mocked Method method) {
		// �e�X�g�p�̃}�b�v�𐶐��B
		LinkedHashMap<String, Integer> testMap = new LinkedHashMap<>();
		testMap.put("�e�X�g1����", 1);
		testMap.put("�e�X�g2����", 0);
		testMap.put("�e�X�g3����", 2);

//		StringBuilder sb = new StringBuilder();
//		// �e�X�g�}�b�v�̕������e�X�g�����s����B
//		for (String key : testMap.keySet()) {
//			String result = method.twice(key, testMap.get(key));
//			if (result != null) {
//				sb.append(result);
//			}
//		}
//		collector.checkThat("�������ʂ̊m�F", sb.toString(), is("�e�X�g1����e�X�g3����e�X�g3����"));
		
		for (String key : testMap.keySet()) {
			// Method�N���X�����b�N���Ă��邽�߁A�{���̏����͍s���Ȃ��B
			// �����̎��s�̂ݍs���B
			method.twice(key, testMap.get(key));
		}

		ArrayList<String> array = new ArrayList<>();
		new Verifications(){{
			// �������擾����B
			// �����擾����ꍇ
			Method method = new Method();
			method.twice(withCapture(array), anyInt); 
			times = 3;
		}};

		// �����̌���
		// hasItem�̏ꍇ
		collector.checkThat(array, hasItem("�e�X�g1����"));
		collector.checkThat(array, hasItem("�e�X�g2����"));
		collector.checkThat(array, hasItem("�e�X�g3����"));
		
		// hasItems�̏ꍇ�@�S���܂Ƃ߂Č��؂ł���B
		collector.checkThat(array, hasItems("�e�X�g1����", "�e�X�g2����", "�e�X�g3����"));
	}
	
	@Test
	public void test007_���b�N���Ă݂�(@Mocked WordsShop wordsShop) {
		new Expectations() {{
			wordsShop.unHappyWord();
			minTimes = 0;
			result = "���݂͂��炢�I�I�I�I";
			wordsShop.happyWord();
			minTimes = 0;
			result = "veryGood!";
		}};

		method.twice("�e�X�g1����", 1);
		method.twice("�e�X�g2����", 0);
		method.twice("�e�X�g3����", -1);
	}

}