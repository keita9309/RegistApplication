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
	 *
	 */
	document.querySelector('#zipCdBtn').addEventListener('click', function() {
		//alert("郵便番号検索ボタンが押下されました。");
		console.log("郵便番号検索ボタンが押下されました。");
		// 郵便番号検索
		searchAddress();
	});

}());

	/**
	 * 郵便番号検索処理
	 *
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

		let kenCd = document.querySelector('#kenCode');
		let address1Input = document.querySelector('#address1-input');
		let address2Input = document.querySelector('#address2-input');
		let address3Input = document.querySelector('#address3-input');
		
		let selectKenCd = document.querySelector('#select-ken-code');
		let address1 = document.querySelector('#address1');
		let address2 = document.querySelector('#address2');
		let address3 = document.querySelector('#address3');
		
		data != null ? kenCd.style.display = "block" : kenCd.style.display = "none";
		data != null ? address1.style.display = "block" : address1.style.display = "none";
		data != null ? address2.style.display = "block" : address2.style.display = "none";
		data != null ? address3.style.display = "block" : address3.style.display = "none";

		selectKenCd.value = data.results[0].prefcode;
		address1Input.value = data.results[0].address1;
		address2Input.value = data.results[0].address2;
		address3Input.value = data.results[0].address3;

	};
	

