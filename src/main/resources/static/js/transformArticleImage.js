/**
 * 处理文章图片，加上width和height属性
 */
(function (w, $) {
    var articleBodyWidth = 600; // 文章固定高度

    /**
     *
     * @param article 原文章内容字符串
     * @param callback 回调，返回一个对象:
     *                  {
     *                      success: true（成功），false（失败）
     *                      new：处理后内容
     *                      old：原文章内容
     *                  }
     *
     */
    var transformArticleImage = function (article, callback) {
        var article_new = '<div>' + article + '</div>'; // 加一层div，方便获取html

        var $article = $(article_new);
        var $image = $article.find('img');

        var w,
            h,
            needLoad = []; // 没有width或height属性的图片，序号保存在这里
        for (var i = 0; i < $image.length; i++) {
            w = $image.eq(i).css('width');
            h = $image.eq(i).css('height');
            w = parseInt(w);
            h = parseInt(h);
            $image.eq(i).css('width', (articleBodyWidth / 100) + 'rem');

            if (w && h) {
                $image.eq(i).css('height', (h * articleBodyWidth / w / 100) + 'rem');
            } else {
                needLoad.push(i);
            }
        }

        /**
         * 加载图片，获取宽高
         * @param j
         */
        var loadImage = function (j) {
            var src = $image.eq(j).attr('src');
            var image = new Image();
            image.src = src;
            image.onload = function () {
                var w = image.width,
                    h = image.height;
                $image.eq(j).css('height', (h * articleBodyWidth / w / 100) + 'rem');

                var index = needLoad.indexOf(j);
                if (index + 1 === needLoad.length) {
                    // 结束
                    callback && callback({
                        success: true,
                        old: article,
                        new: $article.html(),
                    })
                } else {
                    // 下一张图片
                    loadImage(needLoad[index + 1]);
                }
            };

            image.onerror = function () {
                // 获取图片失败
                callback && callback({
                    success: false,
                    old: article,
                    new: '',
                })
            }
        };

        if (needLoad.length > 0) {
            // 有需要加载图片获取宽高
            loadImage(needLoad[0]);
        } else {
            // 没有，直接回调
            callback && callback({
                success: true,
                old: article,
                new: $article.html(),
            })
        }
    };

    window.transformArticleImage = transformArticleImage;
})(window, jQuery);