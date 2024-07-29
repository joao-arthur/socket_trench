use super::{
    about::AboutModel,
    idle_client::{IdleClientModel, IdleClientOutput},
    idle_server::{IdleServerModel, IdleServerOutput},
    main_menu::{MainMenuModel, MainMenuOutput},
    match_play::MatchPlayModel,
};
use gtk::prelude::*;
use relm4::prelude::*;
use rust_i18n::t;

relm4::new_action_group!(pub WindowActions, "app");
relm4::new_stateless_action!(pub GoToAboutWindow, WindowActions, "about");

pub struct WindowModel {
    view: adw::NavigationView,
    main_menu: Controller<MainMenuModel>,
}

#[derive(Debug)]
pub enum WindowInput {
    GoToAboutWindow,
    GoToCreateClient,
    GoToCreateServer,
    GoToClientMatch,
    GoToServerMatch,
}

#[relm4::component(pub)]
impl SimpleComponent for WindowModel {
    type Init = ();
    type Input = WindowInput;
    type Output = ();

    menu! {
        primary_menu: {
            section! {
                &t!("about") => GoToAboutWindow,
            },
        }
    }

    view! {
        main_window = adw::ApplicationWindow {
            add_css_class: "devel",
            set_default_size: (700, 500),
            set_title: Some("Socket Trench"),

            gtk::Box {
                set_orientation: gtk::Orientation::Vertical,
                adw::HeaderBar {
                    pack_end = &gtk::MenuButton {
                        set_icon_name: "open-menu-symbolic",
                        set_menu_model: Some(&primary_menu),
                    },
                },

                #[local_ref]
                view  -> adw::NavigationView {},
            },
        }
    }

    fn init(
        _init: Self::Init,
        window: &Self::Root,
        sender: ComponentSender<Self>,
    ) -> ComponentParts<Self> {
        let main_menu = MainMenuModel::builder().launch(()).forward(
            sender.input_sender(),
            |output| match output {
                MainMenuOutput::GoToCreateServer => WindowInput::GoToCreateServer,
                MainMenuOutput::GoToCreateClient => WindowInput::GoToCreateClient,
            },
        );
        let view = adw::NavigationView::builder().build();
        let model = WindowModel {
            view: view.clone(),
            main_menu,
        };
        let widgets = view_output!();
        Self::create_actions(&widgets, &sender);

        let nav = adw::NavigationPage::builder()
            .title("Main menu")
            .child(model.main_menu.widget())
            .build();

        view.push(&nav);

        ComponentParts { model, widgets }
    }

    fn update(&mut self, message: Self::Input, sender: ComponentSender<Self>) {
        match message {
            Self::Input::GoToAboutWindow => {
                let app = relm4::main_application();
                let main_window = app.windows().first().unwrap().clone();
                let about_window = AboutModel::builder()
                    .transient_for(&main_window)
                    .launch(())
                    .detach();
                about_window.widget().present();
            }
            Self::Input::GoToCreateServer => {
                let nav = adw::NavigationPage::builder()
                    .title("Idle Server")
                    .child(
                        IdleServerModel::builder()
                            .launch(())
                            .forward(sender.input_sender(), |output| match output {
                                IdleServerOutput::GoToMatch => WindowInput::GoToClientMatch,
                            })
                            .widget(),
                    )
                    .build();
                self.view.clone().push(&nav);
            }
            Self::Input::GoToCreateClient => {
                let nav = adw::NavigationPage::builder()
                    .title("Idle Client")
                    .child(
                        IdleClientModel::builder()
                            .launch(())
                            .forward(sender.input_sender(), |output| match output {
                                IdleClientOutput::GoToMatch => WindowInput::GoToServerMatch,
                            })
                            .widget(),
                    )
                    .build();
                self.view.clone().push(&nav);
            }
            Self::Input::GoToClientMatch => {
                let nav = adw::NavigationPage::builder()
                    .title("Idle Client")
                    .child(MatchPlayModel::builder().launch(()).detach().widget())
                    .build();
                self.view.clone().push(&nav);
            }
            Self::Input::GoToServerMatch => {
                let nav = adw::NavigationPage::builder()
                    .title("Idle Client")
                    .child(MatchPlayModel::builder().launch(()).detach().widget())
                    .build();
                self.view.clone().push(&nav);
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
            relm4::actions::RelmAction::<GoToAboutWindow>::new_stateless(move |_| {
                sender.input(<Self as SimpleComponent>::Input::GoToAboutWindow);
            })
        };
        app_actions.add_action(show_about);
        app_actions.register_for_widget(&widgets.main_window);
    }
}
