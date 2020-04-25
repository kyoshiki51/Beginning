
QUnit.test("(サンプル)okの挙動を確かめるテスト", function() {
    QUnit.ok(false, "falseのok関数");    // テストは通らない
    QUnit.ok(true, "trueのok関数");      // テストが通る
    QUnit.ok(1 === '1', "同値演算子のok関数");      // テストは通らない
    QUnit.ok(1 == '1', "等価演算子のok関数");       // テストが通る
});

QUnit.test("アロー関数の確認", () => {
    QUnit.equal(onceTime("word", 3), "wordwordword", "無名関数の動作確認");
    QUnit.equal(arrow1("word", 3), "wordwordword", "アロー関数の動作確認１");
    QUnit.equal(arrow2("word", 3), "wordwordword", "アロー関数の動作確認２");
});

QUnit.test("テストっぽく", () => {
    let words = onceTime(2, 4);
    let num = moreTimes(2,4);

    QUnit.equal(words, "2222", "文字列の連結");
    QUnit.equal(num, 16, "数字の掛け算");
});