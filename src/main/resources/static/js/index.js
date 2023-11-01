(function() {

	/**
	 * ページ読み込み時に実行する処理
	 */
	window.onload = function() {
		// 必須項目の入力・選択状態によって「確認ボタン」を活性化させるかどうかを判定する関数呼び出し
		activeConfirmBtn();
	};

	/**
	 * required属性がついているinputタグ・selectタグが押下された場合に実行する処理
	 */
	document.querySelectorAll('input:required, select:required').forEach(function(el) {
		// 必須項目の入力・選択状態によって「確認ボタン」を活性化させるかどうかを判定する関数呼び出し
		el.addEventListener("change", activeConfirmBtn);
	});

	/**
	 * 必須項目の入力・選択が全て満たされている場合、「確認ボタン」を活性化する。
	 * 対象はrequired属性がついているかつ、disabled属性のついていないもの
	 */
	function activeConfirmBtn() {
		//console.log("event.type : " + event.type);
		let isInput = true;
		document.querySelectorAll('input:required:not([disabled]), select:required:not([disabled])').forEach(function(target) {
			// console.log("target : " + target);
			if (target.type == "radio" || target.type == "checkbox") {
				var radioOrCheckInput = false;
				document.querySelectorAll("input[name = " + target.name + "]").forEach(function(radioOrCheck) {
					radioOrCheckInput = radioOrCheckInput || radioOrCheck.checked;
					console.log("target.name : " + target.name);
				});
				isInput = isInput && radioOrCheckInput;
			} else if (target.type == "select-one") {
				// console.log("target.type : " + target.type);
				isInput = isInput && Number.isInteger(Number.parseInt(target.value));
			} else {
				isInput = isInput && target.value != "";
			}
			document.querySelector('.btn-confirm').disabled = !isInput;
			if (isInput) {
				return;
			}
		});

	};
	
	/**
	 * submit時に、チェックボックスが一つでも選択されている場合、
	 * チェックされていない他の項目のrequired属性を除外
	 *
	 */
	document.form.confirmBtn.addEventListener('click', function() {
		document.querySelectorAll('input:required:not([disabled]), select:required:not([disabled])').forEach(function(target) {
			if (target.type == "checkbox" || target.type == "radio") {
				document.querySelectorAll("input[name = " + target.name + "]").forEach(function(radioOrCheckbox) {
					if (!target.checked) {
						radioOrCheckbox.required = false;
					}
				});
			} else {
				return;
			}
			// フォームの値を送信
			document.form.submit();
		});
	});
	
	/**
	 * 郵便番号検索処理
	 */
	document.querySelector('#zipCdBtn').addEventListener('click', function() {
		//alert("郵便番号検索ボタンが押下されました。");
		console.log("郵便番号検索ボタンが押下されました。");
		// 郵便番号検索
		searchAddress();
	});

	/**
	 * 複数住所選択処理
	 */
	const select = document.querySelector('#select-Multi-ken-code');
	/*document.querySelector('#select-Multi-ken-code').addEventListener('change', function(element) {*/
	select.addEventListener('change', function(element) {
		
		let kenCd = document.querySelector('#kenCd');
		let address1 = document.querySelector('#address1');
		let address2 = document.querySelector('#address2');
		let address3 = document.querySelector('#address3');
		
		let selectKenCd = document.querySelector('#select-ken-code');
		let inputAddress1 = document.querySelector('#address1-input');
		//let inputAddress2 = document.querySelector('#address2-input');
		//alert("複数住所選択処理が実行されました。");
		console.log("複数住所選択処理が実行されました。");
		//console.log("element.target[element.target.selectedIndex].innerText : " + element.target[element.target.selectedIndex].innerText);
		console.log("select.options[select.selectedIndex].text : " + select.options[select.selectedIndex].text);
		console.log("select.options[select.selectedIndex].value : " + select.options[select.selectedIndex].value);
		// 選択した住所のテキスト情報をいったん取得
		let addres1 = select.options[select.selectedIndex].text;
		
		let index1 = addres1.indexOf("都");
		let index2 = addres1.indexOf("道");
		let index3 = addres1.indexOf("府");
		let index4 = addres1.indexOf("県");
		
		// 住所1に値を追加する文字列の開始位置を取得
		let startIndex = index1 + index2 + index3 + index4 + 4; 
		// 選択した値に一致する都道府県を選択
		selectKenCd.value = select.options[select.selectedIndex].value;
		// 都道府県を除いた文字列を、住所1の項目に追加
		inputAddress1.value = addres1.substring(startIndex);
		
		kenCd.style.display = "block";
		address1.style.display = "block";
		address2.style.display = "block";
		address3.style.display = "block";
		
	});
	
	 /**
	 * 郵便番号検索処理
	 */
	async function searchAddress() {
		let api = 'https://zipcloud.ibsnet.co.jp/api/search?zipcode=';
		let postcode = document.querySelector('#zip-code-input').value;
		let url = api + postcode;
		console.log("url : " + url);
		const response = await fetch(url);
		const data = await response.json();
		console.log("data : " + data);
		console.log("data.results[0].address1 : " + data.results[0].address1);
		console.log("data.results[0].address2 : " + data.results[0].address2);
		console.log("data.results[0].address3 : " + data.results[0].address3);
	
		let multiKenCd = document.querySelector('#multiKenCd');
		let kenCd = document.querySelector('#kenCd');
		let address1 = document.querySelector('#address1');
		let address2 = document.querySelector('#address2');
		let address3 = document.querySelector('#address3');
	
		let selectMultiKenCd = document.querySelector('#select-Multi-ken-code');
		let selectKenCd = document.querySelector('#select-ken-code');
		let inputAddress1 = document.querySelector('#address1-input');
		let inputAddress2 = document.querySelector('#address2-input');
	
		// 住所の取得件数が複数件存在する場合
		if (data.results.length > 1) {
			multiKenCd.style.display = "block";
			// 取得件数分ループ
			data.results.forEach(function(addressData) {
				console.log("addressData : " + addressData)
				// optionタグを作成
				let option = document.createElement("option");
				// optionタグのテキストに住所情報を設定する
				option.text = addressData.address1 + addressData.address2 + addressData.address3;
				/*option.text = addressData.address2 + addressData.address3;*/
				// optionタグのvalueに都道府県コードを設定する
				option.value = addressData.prefcode;
				// selectタグの子要素にoptionタグを追加する
				selectMultiKenCd.appendChild(option);
	
			});
	
			// 住所の取得件数が１件の場合
		} else if (data.results.length === 1) {
			kenCd.style.display = "block";
			address1.style.display = "block";
			address2.style.display = "block";
			address3.style.display = "block";
			selectKenCd.value = data.results[0].prefcode;
			/*inputAddress1.value = data.results[0].address1;*/
			inputAddress1.value = data.results[0].address2;
			inputAddress2.value = data.results[0].address3;
	
			// 住所の取得件数が0件の場合
		} else {
			alert("入力された郵便番号に該当する住所が見つかりません。");
		}
	
	};
	
	
}());




