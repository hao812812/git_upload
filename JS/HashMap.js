var HashMap = function () {
    let map = {};
    return {
      //新增資料
      put: function (key, value) {
        map[key] = value;
      },
      //全部key回傳
      keys: function () {//*object.keys -> 傳出陣列

        return Object.keys(map);
      },
      //檢查key值
      contains: function (key) {//*array.includes -> 傳入陣列，判斷是否有包含某個元素

        return Object.keys(map).includes(key);
      },
      //回傳key的value
      get: function (key) {
        return map[key];
      },
      //清空map
      clear: function () {
        map = {};
      }
    };
  };