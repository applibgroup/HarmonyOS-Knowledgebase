import router from '@system.router';

export default {
    data: {
        title: ""
    },
    onInit() {
        this.title = this.$t('strings.world');
    },
    onClick(){
        router.push({
            uri: 'pages/nextpage/nextpage'
        })
    }
}
