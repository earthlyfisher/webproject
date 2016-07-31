(function($) {
	// 当domReady的时候开始初始化
	$(function() {
		var $wrap = $('#uploader'),

		// 图片容器
		$queue = $('<ul class="filelist"></ul>').appendTo(
				$wrap.find('.queueList')),

		// 状态栏，包括进度和控制按钮
		$statusBar = $wrap.find('.statusBar'),

		// 文件总体选择信息。
		$info = $statusBar.find('.info'),

		// 上传按钮
		$upload = $wrap.find('.uploadBtn'),

		// 没选择文件之前的内容。
		$placeHolder = $wrap.find('.placeholder'),

		$progress = $statusBar.find('.progress').hide(),

		// 添加的文件数量
		fileCount = 0,

		// 添加的文件总大小
		fileSize = 0,

		// 优化retina, 在retina下这个值是2
		ratio = window.devicePixelRatio || 1,

		// 缩略图大小
		thumbnailWidth = 110 * ratio, thumbnailHeight = 110 * ratio,

		// 可能有pedding, ready, uploading, confirm, done.
		state = 'pedding',

		// 所有文件的进度信息，key为file id
		percentages = {},
		// 判断浏览器是否支持图片的base64
		isSupportBase64 = (function() {
			var data = new Image();
			var support = true;
			data.onload = data.onerror = function() {
				if (this.width != 1 || this.height != 1) {
					support = false;
				}
			}
			data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
			return support;
		})(),

		// 检测是否已经安装flash，检测flash的版本
		flashVersion = (function() {
			var version;

			try {
				version = navigator.plugins['Shockwave Flash'];
				version = version.description;
			} catch (ex) {
				try {
					version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
							.GetVariable('$version');
				} catch (ex2) {
					version = '0.0';
				}
			}
			version = version.match(/\d+/g);
			return parseFloat(version[0] + '.' + version[1], 10);
		})(),

		supportTransition = (function() {
			var s = document.createElement('p').style, r = 'transition' in s
					|| 'WebkitTransition' in s || 'MozTransition' in s
					|| 'msTransition' in s || 'OTransition' in s;
			s = null;
			return r;
		})(),

		// WebUploader实例
		uploader;

		if (!WebUploader.Uploader.support('flash') && WebUploader.browser.ie) {

			// flash 安装了但是版本过低。
			if (flashVersion) {
				(function(container) {
					window['expressinstallcallback'] = function(state) {
						switch (state) {
						case 'Download.Cancelled':
							alert('您取消了更新！')
							break;

						case 'Download.Failed':
							alert('安装失败')
							break;

						default:
							alert('安装已成功，请刷新！');
							break;
						}
						delete window['expressinstallcallback'];
					};

					var swf = './expressInstall.swf';
					// insert flash object
					var html = '<object type="application/'
							+ 'x-shockwave-flash" data="' + swf + '" ';

					if (WebUploader.browser.ie) {
						html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
					}

					html += 'width="100%" height="100%" style="outline:0">'
							+ '<param name="movie" value="'
							+ swf
							+ '" />'
							+ '<param name="wmode" value="transparent" />'
							+ '<param name="allowscriptaccess" value="always" />'
							+ '</object>';

					container.html(html);

				})($wrap);

				// 压根就没有安转。
			} else {
				$wrap
						.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
			}

			return;
		} else if (!WebUploader.Uploader.support()) {
			alert('Web Uploader 不支持您的浏览器！');
			return;
		}

		// 实例化
		uploader = WebUploader.create({
			pick : {
				id : '#filePicker',
				label : '点击选择文件'
			},
			formData : {
				uid : 123
			},
			swf : '../dist/Uploader.swf',
			chunked : true,
			chunkSize : 5 * 1024 * 1024,
			chunkRetry : 2,
			server : '../user/upload.jhtml',
			// runtimeOrder: 'flash',

			accept : {
				extensions : 'iso,ova',
			},

			// 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
			disableGlobalDnd : true,
			fileNumLimit : 1,
			fileSizeLimit : 10 * 1024 * 1024 * 1024, // 10G
			fileSingleSizeLimit : 10 * 1024 * 1024 * 1024
		// 10G
		});

		// 拖拽时不接受 js, txt 文件。
		uploader.on('dndAccept', function(items) {
			var denied = false, len = items.length, i = 0,
			// 修改js类型
			unAllowed = 'text/plain;application/javascript ';

			for (; i < len; i++) {
				// 如果在列表里面
				if (~unAllowed.indexOf(items[i].type)) {
					denied = true;
					break;
				}
			}

			return !denied;
		});

		// uploader.on('filesQueued', function() {
		// uploader.sort(function( a, b ) {
		// if ( a.name < b.name )
		// return -1;
		// if ( a.name > b.name )
		// return 1;
		// return 0;
		// });
		// });

		// 当有文件添加进来的时候
		uploader.on('fileQueued',
				function(file) {
					var $li = $('<div id="' + file.id
							+ '" class="file-item thumbnail">' + '<img>'
							+ '<div class="info">' + file.name + '</div>'
							+ '</div>'), $img = $li.find('img');

					$list.append($li);

					// 创建缩略图
					uploader.makeThumb(file, function(error, src) {
						if (error) {
							$img.replaceWith('<span>不能预览</span>');
							return;
						}

						$img.attr('src', src);
					}, thumbnailWidth, thumbnailHeight);
				});

		// 文件上传过程中创建进度条实时显示。
		uploader.on('uploadProgress', function(file, percentage) {
			var $li = $('#' + file.id), $percent = $li.find('.progress span');

			// 避免重复创建
			if (!$percent.length) {
				$percent = $('<p class="progress"><span></span></p>').appendTo(
						$li).find('span');
			}

			$percent.css('width', percentage * 100 + '%');
		});

		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader.on('uploadSuccess', function(file) {
			$('#' + file.id).addClass('upload-state-done');
		});

		// 文件上传失败，现实上传出错。
		uploader.on('uploadError', function(file) {
			var $li = $('#' + file.id), $error = $li.find('div.error');

			// 避免重复创建
			if (!$error.length) {
				$error = $('<div class="error"></div>').appendTo($li);
			}

			$error.text('上传失败');
		});

		// 完成上传完了，成功或者失败，先删除进度条。
		uploader.on('uploadComplete', function(file) {
			$('#' + file.id).find('.progress').remove();
		});
	});

})(jQuery);
