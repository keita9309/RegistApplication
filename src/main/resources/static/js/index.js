(function() {
	
	/**
	 * ページ読み込み時に実行する処理
	 */ 
	window.onload = function() {
		// 必須項目の入力・選択状態によって「確認ボタン」を活性化させるかどうかを判定する関数呼び出し
		activeConfirmBtn();
	};
	
	/**
	 * required属性がついているinputタグが押下された場合に実行する処理
	 */
	document.querySelectorAll('input:required, select:required').forEach(function(el) {
		// 必須項目の入力・選択状態によって「確認ボタン」を活性化させるかどうかを判定する関数呼び出し
		el.addEventListener("change", activeConfirmBtn);
	});
	/*document.querySelectorAll('input:required, select:required').forEach(function(el) {
		el.addEventListener("change", function() {*/
			// 必須項目の入力・選択状態によって「確認ボタン」を活性化させるかどうかを判定する関数呼び出し
			//activeConfirmBtn();
		/*});
	});*/
	
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

	
	/*
		document.querySelectorAll('input:required, select:required').forEach(function(el) {
			el.addEventListener("change", function(elements) {
				var isInput = true;
				document.querySelectorAll('input:required:not([disabled]), select:required:not([disabled])').forEach(function(target) {
					console.log("target : " + target);
					if (target.type == "radio" || target.type == "checkbox") {
						var radioOrCheckInput = false;
						document.querySelectorAll("input[name = " + target.name + "]").forEach(function(radioOrCheck) {
							radioOrCheckInput = radioOrCheckInput || radioOrCheck.checked;
							console.log("target.name : " + target.name);
							console.log("radioOrCheck : " + radioOrCheck);
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
			});
			
		});
		*/
		



/*
	const nameDisplay = document.querySelector('#nameDisplay');

	nameDisplay.addEventListener("click", function() {
			const nameInput = document.querySelector('.name-input');
			console.log("nameDisplay : " + nameDisplay);
			console.log("nameInput : " + nameInput);
			console.log("length : " + document.querySelectorAll('input:required').length);
			console.log("value : " + document.querySelectorAll('input:required')[0].value);
			//const lastName = document.querySelector('#lastName');
			//const firstName = document.querySelector('#firstName');
			nameDisplay.checked ? nameInput.style.display = "block" : nameInput.style.display = "none";
			//element.checked ? firstName.style.display = "block" : firstName.style.display = "none";
	});
});*/

}());
