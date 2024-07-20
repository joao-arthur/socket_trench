use relm4::prelude::*;
use rust_i18n::{i18n, t};
use sys_locale::get_locale;

rust_i18n::i18n!("locales");

mod app;

fn main() {
    let locale = get_locale().unwrap_or_else(|| String::from("en-US"));
    println!("The current locale is {}", locale);
    i18n!("locales", fallback = "en");
    rust_i18n::set_locale(&"pt-BR");
    println!("{}", t!("hello_world"));
    println!("{:?}", rust_i18n::available_locales!());

    let app = RelmApp::new(app::APP_ID);
    app.run::<app::AppModel>(());
}
