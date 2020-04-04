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

// JUnitサンプル
class Method {
	
	WordsShop ws = new WordsShop();
	
	// デフォルトコンストラクタ
	public Method() {}

	/**
	 * 引数で渡された文字列を、指定された回数だけ結合して返却するメソッド。
	 * 結合結果も出力する。誉めてもあげる。
	 * 0以下を渡してきた輩には、nullと罵倒の言葉を授ける。
	 * @return 結合結果
	*/
	public String twice(String a, int num) {
		if (num > 0 ) {
			StringBuilder aNum = new StringBuilder(a);
			for(int i = 1; i < num; i++) {
				aNum.append(a);
			}
			System.out.println("結合結果：" + aNum);
			System.out.println(ws.happyWord());
			return aNum.toString();
		} else {
			System.out.println(ws.unHappyWord());
			return null;
		}
	}
}

/**
 * 言葉を返却するクラス
 * @author 匿名希望
 *
 */
class WordsShop {
	// defaultConstractor
	public WordsShop() {}
	
	public String unhappy = "ばーか";
	public String happy = "すごいね";
	
	/**
	 * 誰も幸せにしないメソッド
	 * @return 罵倒の言葉
	 */
	public String unHappyWord() {
		return unhappy;
	}
	
	/**
	 * みんな幸せなメソッド
	 * `return 誉め言葉
	 */
	public String happyWord() {
		return happy;
	}
}

@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class YkSampleTest {
	// @RuleでTestNameを定義するとテストメソッド名が参照できる。
	@Rule
	public TestName testName = new TestName();

	// ErrorCollecctorを使用するために定義する。
	@Rule
	public ErrorCollector collector = new ErrorCollector();

	// @Testedでインスタンスの生成ができる。（毎回生成する必要がない。）で引数なしのコンストラクタが呼ばれる。
	@Tested
	private Method method;
	
//	// @Mockedでモックされたインスタンスを生成。（モック+再定義なしのため、何も処理を行わなくなる。）
//	@Mocked
//	WordsShop wordsShop_;
	
	@Before
	public void before() {
		System.out.println("【" + testName.getMethodName() + "】:Start!");
	}

	@After
	public void after() {
		System.out.println("【" + testName.getMethodName() + "】:End!");
		System.out.println("");
	}
	
	@Test
	public void test001_普通にテスト_1回呼び出し() {
		String result = method.twice("3回繰り返すよ", 3);
		collector.checkThat(result, is("3回繰り返すよ3回繰り返すよ3回繰り返すよ"));
	}
	
	
	@Test
	public void test002_普通にテスト_3回呼び出し() {
		// テスト用のマップを生成。
		LinkedHashMap<String, Integer> testMap = new LinkedHashMap<>();
		testMap.put("テスト1だよ", 1);
		testMap.put("テスト2だよ", 2);
		testMap.put("テスト3だよ", 3);
		
		StringBuilder sb = new StringBuilder();
		// テストマップの分だけテストを実行する。
		for (String key : testMap.keySet()) {
			String result = method.twice(key, testMap.get(key));
			if (result != null) {
				sb.append(result);
			}
		}
		collector.checkThat("結果の確認", sb.toString(), is("テスト1だよテスト2だよテスト2だよテスト3だよテスト3だよテスト3だよ"));
	}
	
	@Test
	public void test003_普通にテスト_0以下を1回呼び出し() {
		String result = method.twice("0回繰り返すよ", 0);
		collector.checkThat(result, is(nullValue()));
	}
	
	@Test
	public void test004_普通にテスト_0以下を3回呼び出し() {
		// テスト用のマップを生成。
		LinkedHashMap<String, Integer> testMap = new LinkedHashMap<>();
		testMap.put("テスト1だよ", 0);
		testMap.put("テスト2だよ", 1);
		testMap.put("テスト3だよ", -1);

		StringBuilder sb = new StringBuilder();
		// テストマップの分だけテストを実行する。
		for (String key : testMap.keySet()) {
			String result = method.twice(key, testMap.get(key));
			if (result != null) {
				sb.append(result);
			}
		}
		collector.checkThat("総合結果の確認", sb.toString(), is("テスト2だよ"));
	}
	
	@Test
	public void test005_Expectations(@Mocked Method method) {
		new Expectations(){{
			method.twice(anyString, anyInt); 
			minTimes = 0;
			maxTimes = 3;
			returns("1","2","3");
		}};
		
		// テスト用のマップを生成。
		LinkedHashMap<String, Integer> testMap = new LinkedHashMap<>();
		testMap.put("テスト1だよ", 1);
		testMap.put("テスト2だよ", 2);
		testMap.put("テスト3だよ", 0);

		StringBuilder sb = new StringBuilder();
		// テストマップの分だけテストを実行する。
		for (String key : testMap.keySet()) {
			String result = method.twice(key, testMap.get(key));
			if (result != null) {
				sb.append(result);
			}
		}
		collector.checkThat("総合結果の確認", sb.toString(), is("123"));
	}

	@Test
	public void test006_Verifications(@Mocked Method method) {
		// テスト用のマップを生成。
		LinkedHashMap<String, Integer> testMap = new LinkedHashMap<>();
		testMap.put("テスト1だよ", 1);
		testMap.put("テスト2だよ", 0);
		testMap.put("テスト3だよ", 2);

//		StringBuilder sb = new StringBuilder();
//		// テストマップの分だけテストを実行する。
//		for (String key : testMap.keySet()) {
//			String result = method.twice(key, testMap.get(key));
//			if (result != null) {
//				sb.append(result);
//			}
//		}
//		collector.checkThat("総合結果の確認", sb.toString(), is("テスト1だよテスト3だよテスト3だよ"));
		
		for (String key : testMap.keySet()) {
			// Methodクラスをモックしているため、本来の処理は行われない。
			// 処理の実行のみ行う。
			method.twice(key, testMap.get(key));
		}

		ArrayList<String> array = new ArrayList<>();
		new Verifications(){{
			// 引数を取得する。
			// 複数取得する場合
			Method method = new Method();
			method.twice(withCapture(array), anyInt); 
			times = 3;
		}};

		// 引数の検証
		// hasItemの場合
		collector.checkThat(array, hasItem("テスト1だよ"));
		collector.checkThat(array, hasItem("テスト2だよ"));
		collector.checkThat(array, hasItem("テスト3だよ"));
		
		// hasItemsの場合　全部まとめて検証できる。
		collector.checkThat(array, hasItems("テスト1だよ", "テスト2だよ", "テスト3だよ"));
	}
	
	@Test
	public void test007_モックしてみる(@Mocked WordsShop wordsShop) {
		new Expectations() {{
			wordsShop.unHappyWord();
			minTimes = 0;
			result = "きみはえらい！！！！";
			wordsShop.happyWord();
			minTimes = 0;
			result = "veryGood!";
		}};

		method.twice("テスト1だよ", 1);
		method.twice("テスト2だよ", 0);
		method.twice("テスト3だよ", -1);
	}

}