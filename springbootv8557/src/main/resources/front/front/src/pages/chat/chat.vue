<template>
    <!--          消息整体-->
    <div class="container">
        <div :class="message.sender === 'me' ? 'chat-message-me' : 'chat-message-other'"
            :style="{ 'padding-bottom': messages.length - 1 === index ? '2rem' : 'none' }"
            v-for="(message, index) in messages" :key="index">

            <!--            消息头像-->
            <div
                :class="message.sender === 'me' ? 'message-me-asWhole-headPortrait' : 'message-other-asWhole-headPortrait'">
                <img src="~@/assets/head-ai.jpg" v-if="message.sender === 'other'" class="examineeFace_logo_style">
                <img src="~@/assets/me.jpg" v-if="message.sender === 'me'" class="examineeFace_logo_style">
            </div>
            <!--          消息-->
            <div :class="message.sender === 'me' ? 'message-me-asWhole-right' : 'message-other-asWhole-right'">
                <!--            消息上面-->
                <div :class="message.sender === 'me' ? 'message-me-asWhole-top' : 'message-other-asWhole-top'">
                    {{ message.sender === 'me' ? "我" : "小果" }}
                </div>
                <!--          消息内容-->
                <div :class="message.sender === 'me' ? 'message-me' : 'message-other'">
                    {{ message.content }}
                </div>
            </div>
        </div>
        <div class="input-style">
            <el-input class="input-box" v-model="input" placeholder="请输入内容"></el-input>
            <el-button class="input-button" @click="send_question">发送</el-button>
        </div>
    </div>

</template>

<script>
export default {
    data() {
        return {
            input: '',
            output: '',
            messages: [
                { sender: 'other', content: '你好，我是智能助手小果，有什么需要帮助的吗？' },
            ],
        }
    },
    created() {
        window.scrollTo(0, 0)
    },
    methods: {
        send_question() {
            if (this.input.length === 0) {
                return;
            } else {
                this.messages.push({ sender: 'me', content: this.input });
                this.messages.push({ sender: 'other', content: "..." });
                var temp = this.input;
                this.input = "";
                console.log(this.input);
                this.$http.get("talk", {params : { "question": temp }}).then(res => {
                    console.log(res);
                    this.output = res.body.msg;
                    // console.log(Array.isArray(this.messages))
                    this.messages[this.messages.length-1].content = this.output;
                    // this.messages.push({ sender: 'other', content: this.output });
                }

                )

            }
        },
        answer(question) {
            return "哈哈，你真是小聪明！";
        }
    }
};
</script>

<style scoped lang="scss">
.chat-message-other {
    /*background-color: red;*/
    display: flex;
    padding-left: 1rem;
    padding-top: 1rem;
}

.chat-message-me {
    /*background-color: red;*/
    display: flex;
    padding-right: 1rem;
    padding-top: 1rem;
    flex-direction: row-reverse;
    /* 将子div的顺序反转 */

}

.message-me-asWhole-headPortrait {
    padding: 3px;
}

.message-other-asWhole-headPortrait {
    padding: 3px;
}

.message-me-asWhole-right {
    display: flex;
    flex-direction: column;
    /* 设置子元素垂直排列 */
    margin-left: 0.1rem;
}

.message-other-asWhole-right {
    display: flex;
    flex-direction: column;
    /* 设置子元素垂直排列 */
    margin-left: 0.1rem;
}

.message-me-asWhole-top {
    padding: 3px;
    /* font-size: 12px; */
    font-family: 微软雅黑;
    padding: 3px;
    color: rgba(134, 144, 156, 1);
    text-align: right;
}

.message-other-asWhole-top {
    padding: 3px;
    /* font-size: 12px; */
    font-family: 微软雅黑;
    padding: 3px;
    color: rgba(134, 144, 156, 1);
}

.message-me {
    background-color: rgba(242, 243, 245, 1);
    max-width: 280px;
    word-wrap: break-word;
    /* 处理英文单词换行 */
    word-break: break-all;
    /* 处理中文换行 */
    display: inline-block;
    /*将div元素转换为行内块元素*/
    width: auto;
    /* 宽度根据文本宽度自动调正*/
    padding: 6px 12px;
    border-radius: 4px;
}

.message-other {
    background-color: rgba(242, 243, 245, 1);
    max-width: 280px;
    word-wrap: break-word;
    /* 处理英文单词换行 */
    word-break: break-all;
    /* 处理中文换行 */
    display: inline-block;
    /*将div元素转换为行内块元素*/
    width: auto;
    /* 宽度根据文本宽度自动调正*/
    padding: 6px 12px;
    border-radius: 4px;
}

.container {
    min-height: 100vh;
}

.examineeFace_logo_style {
    width: 30px;
}

.input-style {
    width: 100%;
    display: flex;
    position: fixed;
    bottom: 5px;
}

.input-style.input_box {
    margin-left: 50px;
    margin-right: 50px;
}

.input-style.input_button {
    margin-right: 10px;
}
</style>
