use super::{
    about::{AboutInit, AboutModel},
    idle_client::{IdleClientInit, IdleClientModel},
    main_menu::{MainMenuInit, MainMenuModel},
};
use gtk::prelude::*;
use libadwaita::prelude::NavigationPageExt;
use relm4::prelude::*;
use rust_i18n::t;

relm4::new_action_group!(pub WindowActions, "app");
relm4::new_stateless_action!(pub ShowAboutWindow, WindowActions, "about");

pub struct WindowModel {
    main_menu: Controller<MainMenuModel>,
    idle_client: Controller<IdleClientModel>,
}

pub struct WindowInit;

#[derive(Debug)]
pub enum WindowInput {
    ShowAboutWindow,
}

#[derive(Debug)]
pub enum WindowOutput {}

#[relm4::component(pub)]
impl SimpleComponent for WindowModel {
    type Init = WindowInit;
    type Input = WindowInput;
    type Output = WindowOutput;

    menu! {
        primary_menu: {
            section! {
                &t!("about") => ShowAboutWindow,
            },
        }
    }

    view! {
        main_window = adw::ApplicationWindow {
            add_css_class: "devel",
            set_default_size: (1024, 600),
            set_title: Some("Socket Trench"),

            gtk::Box {
                set_orientation: gtk::Orientation::Vertical,
                adw::HeaderBar {
                    pack_end = &gtk::MenuButton {
                        set_icon_name: "open-menu-symbolic",
                        set_menu_model: Some(&primary_menu),
                    },
                },
                #[name = "navpage"]
                adw::NavigationView { }


            },
        }
    }

    fn init(
        _init: Self::Init,
        window: &Self::Root,
        sender: ComponentSender<Self>,
    ) -> ComponentParts<Self> {
        let main_menu = MainMenuModel::builder().launch(MainMenuInit).detach();
        let idle_client = IdleClientModel::builder().launch(IdleClientInit).detach();
        let model = WindowModel {
            main_menu,
            idle_client,
        };
        let widgets = view_output!();
        Self::create_actions(&widgets, &sender);
       
        let nav = adw::NavigationPage::builder().child(model.main_menu.widget()).build();

        widgets.navpage.push(&nav);
       
        ComponentParts { model, widgets }
    }

    //fn pre_view() {
    //    match model.page {
    //        Page::IdleClient => stack.set_visible_child(login_page),
    //        Page::MainMenu => stack.set_visible_child(main_page),
    //    }
    //}

    fn update(&mut self, message: Self::Input, _sender: ComponentSender<Self>) {
        match message {
            Self::Input::ShowAboutWindow => {
                let app = relm4::main_application();
                let main_window = app
                    .windows()
                    .first()
                    .unwrap()
                    .clone();
                let about_window = AboutModel::builder()
                    .transient_for(&main_window)
                    .launch(AboutInit)
                    .detach();
                about_window.widget().present();
                //idle_client::IdleClientModel::builder()
                //    .launch(idle_client::IdleClientInit)
                //    .widget()
                //    .present();
            }
        }
    }
}

impl WindowModel {
    pub fn create_actions(
        widgets: &<Self as SimpleComponent>::Widgets,
        sender: &ComponentSender<Self>,
    ) {
        let mut app_actions = relm4::actions::RelmActionGroup::<WindowActions>::new();
        let show_about = {
            let sender = sender.clone();
            relm4::actions::RelmAction::<ShowAboutWindow>::new_stateless(move |_| {
                sender.input(<Self as SimpleComponent>::Input::ShowAboutWindow);
            })
        };
        app_actions.add_action(show_about);
        app_actions.register_for_widget(&widgets.main_window);
    }
}
