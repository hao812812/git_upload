var HashMap = function () {
    let map = {};
    return {
      //新增資料
      put: function (key, value) {
        map[key] = value;
      },
      //全部key回傳
      keys: function () {
        const keySet = [];
        for (let key in map) {
          keySet.push(key);
        }
        return keySet;
      },
      //檢查key值
      contains: function (key) {

        for (let k in map) {
          if (k === key) {
            return true;
          }
        }
        return false;
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